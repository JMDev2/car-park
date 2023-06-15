package com.ekenya.rnd.baseapp.di.helpers.features;

import com.ekenya.rnd.baseapp.di.helpers.activities.AddressableActivity;
import com.ekenya.rnd.common.Constants;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class Modules {


    private static final List<FeatureModule> modules;

    public static final Modules INSTANCE;

    public final FeatureModule getModuleFromName(String moduleName) {

        Iterator var4 = modules.iterator();

        FeatureModule it;
        do {
            if (!var4.hasNext()) {
                throw new IllegalArgumentException(moduleName + " is not found");
            }
            Object element$iv = var4.next();
            it = (FeatureModule) element$iv;
        } while (!it.getName().equalsIgnoreCase(moduleName));

        return it;
    }

    private Modules() {
    }

    static {
        Modules var0 = new Modules();
        INSTANCE = var0;
        modules = Arrays.asList(new FeatureModule[]{
                FeatureTourism.INSTANCE,
                FeatureSupport.INSTANCE,
                FeatureDashboard.INSTANCE
                }.clone()
        );
    }

    //***************************************************************
    public static final class FeatureTourism implements FeatureModule, AddressableActivity {

        private static final String name;

        private static final String injectorName;

        private static final String className;

        public static final FeatureTourism INSTANCE;

        public String getName() {
            return name;
        }

        public String getInjectorName() {
            return injectorName;
        }

        private FeatureTourism() {
        }

        static {
            FeatureTourism var0 = new FeatureTourism();
            INSTANCE = var0;
            name = "etourism";
            injectorName = Constants.BASE_PACKAGE_NAME+"."+name+".di.TourismInjector";
            className = Constants.BASE_PACKAGE_NAME +"."+name+".MainActivity";
        }

        @Override
        public String getClassName() {
            return className;
        }
    }

    //***************************************************************
    public static final class FeatureSupport implements FeatureModule {

        private static final String name;

        private static final String injectorName;

        public static final FeatureSupport INSTANCE;

        public String getName() {
            return name;
        }

        public String getInjectorName() {
            return injectorName;
        }

        private FeatureSupport() {
        }

        static {
            FeatureSupport var0 = new FeatureSupport();
            INSTANCE = var0;
            name = "support";
            injectorName = Constants.BASE_PACKAGE_NAME+".support.di.SupportInjector";
        }
    }
    //***************************************************************

    public static final class FeatureDashboard implements FeatureModule, AddressableActivity {

        public static final FeatureDashboard INSTANCE;
        private static final String name;
        private static final String injectorName;
        private static final String className;

        static {
            FeatureDashboard var0 = new FeatureDashboard();
            INSTANCE = var0;
            name = "dashboard";
            injectorName = Constants.MAIN_PACKAGE_NAME+".di.DashboardInjector";
            className = Constants.MAIN_PACKAGE_NAME +".ui.MainActivity";
        }

        private FeatureDashboard() {
        }

        public String getName() {
            return name;
        }

        public String getInjectorName() {
            return injectorName;
        }

        @Override
        public String getClassName()


        {
            return className;
        }
    }

    //***************************************************************

//    public static final class FeatureOnborading implements FeatureModule, AddressableActivity {
//
//        public static final FeatureOnborading INSTANCE;
//        private static final String name;
//        private static final String injectorName;
//        private static final String className;
//
//        static {
//            FeatureOnborading var0 = new FeatureOnborading();
//            INSTANCE = var0;
//            name = "onborading";
//            injectorName = Constants.BASE_PACKAGE_NAME+"."+name+".di.TourismInjector";
//            className = Constants.BASE_PACKAGE_NAME +"."+name+".MainActivity";
//        }
//
//        private FeatureOnborading() {
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getInjectorName() {
//            return injectorName;
//        }
//
//        @Override
//        public String getClassName()
//
//
//        {
//            -----------------------
//        }
//    }

    //***************************************************************


}
