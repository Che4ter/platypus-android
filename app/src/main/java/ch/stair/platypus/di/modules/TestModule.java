//package ch.stair.platypus.di.modules;
//
//import android.content.Context;
//
//import javax.inject.Singleton;
//
//import ch.stair.platypus.App;
//import ch.stair.platypus.domain.Repository;
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Dagger module that provides objects which will live during the application lifecycle.
// */
//@Module
//public class TestModule {
//    private final App application;
//
//    public TestModule(App application) {
//        this.application = application;
//    }
//
//    @Provides @Singleton Context provideApplicationContext() {
//        return this.application;
//    }
//
//    @Provides Repository provideRepository() {
//        return null;
//    }
//
////  @Provides Repository provideRepository(final ObjectBoxRepository objectBoxRepository) {
////    return objectBoxRepository;
////  }
////
////  @Provides @Singleton BoxStore provideBoxStore() {
////    return MyObjectBox.builder().androidContext(this.application).build();
////  }
//}
