package in.ults.ipms.di.component;

import dagger.Component;
import in.ults.ipms.di.PerFragment;
import in.ults.ipms.di.module.FragmentModule;
import in.ults.ipms.ui.auth.forgotpassword.ForgotPasswordFragment;
import in.ults.ipms.ui.auth.login.LoginFragment;
import in.ults.ipms.ui.buildingassets.building.BuildingDetailsFragment;
import in.ults.ipms.ui.buildingassets.establishment.EstablishmentDetailsFragment;
import in.ults.ipms.ui.buildingassets.floorandroof.FloorAndRoofDetailsFragment;
import in.ults.ipms.ui.buildingassets.home.BuildingAssetHomeFragment;
import in.ults.ipms.ui.buildingassets.location.LocationDetailsFragment;
import in.ults.ipms.ui.buildingassets.member.MemberDetailsFragment;
import in.ults.ipms.ui.buildingassets.memberlist.MemberListFragment;
import in.ults.ipms.ui.buildingassets.otherproperty.PropertyOtherDetailsFragment;
import in.ults.ipms.ui.buildingassets.owner.OwnerDetailsFragment;
import in.ults.ipms.ui.buildingassets.ownerlist.OwnerListFragment;
import in.ults.ipms.ui.buildingassets.property.PropertyDetailsFragment;
import in.ults.ipms.ui.buildingassets.survey.SurveyDetailsFragment;
import in.ults.ipms.ui.buildingassets.tax.TaxDetailsFragment;
import in.ults.ipms.ui.buildingassets.taxlist.TaxListFragment;
import in.ults.ipms.ui.buildingassets.tenant.TenantDetailsFragment;
import in.ults.ipms.ui.otherassets.home.OtherAssetsHomeFragment;
import in.ults.ipms.ui.otherassets.mastlight.HighLowMastLightDetailsFragment;
import in.ults.ipms.ui.utility.bridge.BridgeDetailsFragment;
import in.ults.ipms.ui.utility.busstand.BusStandDetailsFragment;
import in.ults.ipms.ui.utility.busbay.BusBayDetailsFragment;
import in.ults.ipms.ui.utility.busstop.BusStopDetailsFragment;
import in.ults.ipms.ui.utility.canal.CanalDetailsFragment;
import in.ults.ipms.ui.utility.canalline.CanalLineDetailsFragment;
import in.ults.ipms.ui.utility.culvert.CulvertDetailsFragment;
import in.ults.ipms.ui.utility.divider.DividerDetailsFragment;
import in.ults.ipms.ui.utility.drainage.details.DrainageDetailsFragment;
import in.ults.ipms.ui.utility.drainage.map.DrainageMapFragment;
import in.ults.ipms.ui.utility.garbage.GarbageDetailsFragment;
import in.ults.ipms.ui.utility.home.UtilityHomeFragment;
import in.ults.ipms.ui.utility.hump.RoadHumpDetailsFragment;
import in.ults.ipms.ui.utility.junction.RoadJunctionDetailsFragment;
import in.ults.ipms.ui.utility.mobiletower.MobileTowerDetailsFragment;
import in.ults.ipms.ui.utility.park.ParkDetailsFragment;
import in.ults.ipms.ui.utility.parking.ParkingDetailsFragment;
import in.ults.ipms.ui.utility.playground.PlaygroundDetailsFragment;
import in.ults.ipms.ui.utility.road.details.RoadDetailsFragment;
import in.ults.ipms.ui.utility.road.map.RoadMapFragment;
import in.ults.ipms.ui.utility.signboard.RoadSignboardDetailsFragment;
import in.ults.ipms.ui.utility.stadium.StadiumDetailsFragment;
import in.ults.ipms.ui.utility.statue.StatueDetailsFragment;
import in.ults.ipms.ui.utility.streettap.StreetTapDetailsFragment;
import in.ults.ipms.ui.utility.tank.TankDetailsFragment;
import in.ults.ipms.ui.utility.taxistand.TaxiStandDetailsFragment;
import in.ults.ipms.ui.utility.transformer.TransformerDetailsFragment;
import in.ults.ipms.ui.utility.well.WellDetailsFragment;
import in.ults.ipms.ui.waterbody.home.WaterBodyHomeFragment;
import in.ults.ipms.ui.waterbody.pond.PondDetailsFragment;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(LoginFragment fragment);

    void inject(ForgotPasswordFragment fragment);

    void inject(BuildingAssetHomeFragment fragment);

    void inject(BuildingDetailsFragment fragment);

    void inject(PropertyDetailsFragment fragment);

    void inject(OwnerDetailsFragment fragment);

    void inject(MemberDetailsFragment fragment);

    void inject(TenantDetailsFragment fragment);

    void inject(SurveyDetailsFragment fragment);

    void inject(TaxDetailsFragment fragment);

    void inject(EstablishmentDetailsFragment fragment);

    void inject(PropertyOtherDetailsFragment fragment);

    void inject(LocationDetailsFragment fragment);

    void inject(in.ults.ipms.ui.buildingassets.road.RoadDetailsFragment fragment);

    void inject(MemberListFragment fragment);

    void inject(UtilityHomeFragment fragment);

    void inject(BridgeDetailsFragment fragment);

    void inject(RoadMapFragment fragment);

    void inject(RoadDetailsFragment fragment);

    void inject(TaxListFragment fragment);

    void inject(OwnerListFragment fragment);

    void inject(CulvertDetailsFragment fragment);

    void inject(DividerDetailsFragment fragment);

    void inject(ParkingDetailsFragment fragment);

    void inject(PlaygroundDetailsFragment fragment);

    void inject(RoadHumpDetailsFragment fragment);

    void inject(RoadJunctionDetailsFragment fragment);

    void inject(RoadSignboardDetailsFragment fragment);

    void inject(DrainageMapFragment fragment);

    void inject(DrainageDetailsFragment fragment);

    void inject(WaterBodyHomeFragment fragment);

    void inject(PondDetailsFragment fragment);

    void inject(FloorAndRoofDetailsFragment fragment);

    void inject(BusBayDetailsFragment fragment);

    void inject(BusStandDetailsFragment fragment);

    void inject(BusStopDetailsFragment fragment);

    void inject(CanalDetailsFragment fragment);

    void inject(CanalLineDetailsFragment fragment);

    void inject(GarbageDetailsFragment fragment);

    void inject(MobileTowerDetailsFragment fragment);

    void inject(ParkDetailsFragment fragment);

    void inject(StadiumDetailsFragment fragment);

    void inject(StatueDetailsFragment fragment);

    void inject(StreetTapDetailsFragment fragment);

    void inject(TankDetailsFragment fragment);

    void inject(TaxiStandDetailsFragment fragment);

    void inject(TransformerDetailsFragment fragment);

    void inject(WellDetailsFragment fragment);

    void inject(OtherAssetsHomeFragment fragment);

    void inject(HighLowMastLightDetailsFragment fragment);

}
