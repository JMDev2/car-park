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
                FeatureDashboard.INSTANCE,
                FeatureOnboarding.INSTANCE
                }.clone()
        );
    }

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

    public static final class FeatureOnboarding implements FeatureModule, AddressableActivity {

        public static final FeatureOnboarding INSTANCE;
        private static final String name;
        private static final String injectorName;
        private static final String className;

        static {
            FeatureOnboarding var0 = new FeatureOnboarding();
            INSTANCE = var0;
            name = "onboarding";
            injectorName = Constants.BASE_PACKAGE_NAME+"."+name+".di.OnboardingInjector";
            className = Constants.BASE_PACKAGE_NAME +"."+name+".ui.MainActivity";

//            injectorName =  "com.ekenya.rnd.onboarding.di.OnboardingInjector";
//            className =  "com.ekenya.rnd.onboarding.ui.MainActivity";
        }

        private FeatureOnboarding() {
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


}
