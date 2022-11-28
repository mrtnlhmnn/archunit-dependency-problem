package architecturetests;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class OnionDependenciesTest {

    public static final String PACKAGE_PREFIX = "org.example";

    public static JavaClasses allJavaClassesToCheck =
            new ClassFileImporter().importPackages(PACKAGE_PREFIX);

    class Layer {
        String name;
        String pkg;
        Layer(String name, String pkg) { this.name = name; this.pkg = pkg; }
    }

    @Test
    void test_onion_architecture() {
        // arrange
        Layer restLayer        = new Layer("Rest",        PACKAGE_PREFIX + ".rest..");
        Layer webLayer         = new Layer("Web",         PACKAGE_PREFIX + ".web..");
        Layer applicationLayer = new Layer("Application", PACKAGE_PREFIX + ".application..");
        Layer domainLayer      = new Layer("Domain",      PACKAGE_PREFIX + ".domain..");
        Layer persistenceLayer = new Layer("Persistence", PACKAGE_PREFIX + ".persistence..");

        Architectures.LayeredArchitecture layeredArchitecture =  Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInAnyPackage(PACKAGE_PREFIX + "..")
            .withOptionalLayers(true)
            .layer(restLayer.name).definedBy(restLayer.pkg)
            .layer(webLayer.name).definedBy(webLayer.pkg)
            .layer(applicationLayer.name).definedBy(applicationLayer.pkg)
            .layer(domainLayer.name).definedBy(domainLayer.pkg)
            .layer(persistenceLayer.name).definedBy(persistenceLayer.pkg)
            ;

        // act, assert
        layeredArchitecture
            // web
            .whereLayer(webLayer.name)
            .mayOnlyAccessLayers(applicationLayer.name, domainLayer.name)

            // rest
            .whereLayer(restLayer.name)
            .mayOnlyAccessLayers(applicationLayer.name, domainLayer.name)

            // persistence
            .whereLayer(persistenceLayer.name)
            .mayOnlyBeAccessedByLayers(applicationLayer.name)

            // check
            .because("we want to enforce the onion architecture inside each component")
            .check(allJavaClassesToCheck);
    }

}
