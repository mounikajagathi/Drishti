package in.ults.ipms.di.component;

import dagger.Component;
import in.ults.ipms.di.PerBottomSheet;
import in.ults.ipms.di.module.BottomSheetModule;
import in.ults.ipms.ui.dashboard.basemap.BaseMapBottomSheet;
import in.ults.ipms.ui.dashboard.count.CountBottomSheet;
import in.ults.ipms.ui.dashboard.layers.LayersBottomSheet;
import in.ults.ipms.ui.dashboard.legends.LegendsBottomSheet;
import in.ults.ipms.ui.dashboard.opacity.OpacityBottomSheet;
import in.ults.ipms.ui.dashboard.search.filter.BasicFilterBottomSheet;
import in.ults.ipms.ui.dashboard.settings.SettingsBottomSheet;
import in.ults.ipms.ui.mapoverlays.MapOverlayBottomSheet;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */
@PerBottomSheet
@Component(dependencies = ApplicationComponent.class, modules = BottomSheetModule.class)
public interface BottomSheetComponent {
    void inject(BaseMapBottomSheet bottomSheet);
    void inject(LayersBottomSheet bottomSheet);
    void inject(LegendsBottomSheet bottomSheet);
    void inject(OpacityBottomSheet bottomSheet);
    void inject(BasicFilterBottomSheet bottomSheet);
    void inject(SettingsBottomSheet bottomSheet);
    void inject(MapOverlayBottomSheet bottomSheet);
    void inject(CountBottomSheet bottomSheet);

}
