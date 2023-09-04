package in.ults.ipms.di.module;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import in.ults.ipms.adapters.BuildingAssetHomeAdapter;
import in.ults.ipms.adapters.DisabilityAdapter;
import in.ults.ipms.adapters.FloorAreaAdapter;
import in.ults.ipms.adapters.FloorTypeAdapter;
import in.ults.ipms.adapters.MembersListAdapter;
import in.ults.ipms.adapters.OwnerListAdapter;
import in.ults.ipms.adapters.RoofTypeAdapter;
import in.ults.ipms.adapters.TaxListAdapter;
import in.ults.ipms.adapters.UtilityHomeAdapter;
import in.ults.ipms.adapters.WaterBodyHomeAdapter;
import in.ults.ipms.di.PerFragment;
import in.ults.ipms.ui.auth.forgotpassword.ForgotPasswordInteractor;
import in.ults.ipms.ui.auth.forgotpassword.ForgotPasswordPresenter;
import in.ults.ipms.ui.auth.forgotpassword.IForgotPasswordInteractor;
import in.ults.ipms.ui.auth.forgotpassword.IForgotPasswordPresenter;
import in.ults.ipms.ui.auth.forgotpassword.IForgotPasswordView;
import in.ults.ipms.ui.auth.login.ILoginInteractor;
import in.ults.ipms.ui.auth.login.ILoginPresenter;
import in.ults.ipms.ui.auth.login.ILoginView;
import in.ults.ipms.ui.auth.login.LoginInteractor;
import in.ults.ipms.ui.auth.login.LoginPresenter;
import in.ults.ipms.ui.base.BaseActivity;
import in.ults.ipms.ui.buildingassets.building.BuildingDetailsInteractor;
import in.ults.ipms.ui.buildingassets.building.BuildingDetailsPresenter;
import in.ults.ipms.ui.buildingassets.building.IBuildingDetailsInteractor;
import in.ults.ipms.ui.buildingassets.building.IBuildingDetailsPresenter;
import in.ults.ipms.ui.buildingassets.building.IBuildingDetailsView;
import in.ults.ipms.ui.buildingassets.establishment.EstablishmentDetailsInteractor;
import in.ults.ipms.ui.buildingassets.establishment.EstablishmentDetailsPresenter;
import in.ults.ipms.ui.buildingassets.establishment.IEstablishmentDetailsInteractor;
import in.ults.ipms.ui.buildingassets.establishment.IEstablishmentDetailsPresenter;
import in.ults.ipms.ui.buildingassets.establishment.IEstablishmentDetailsView;
import in.ults.ipms.ui.buildingassets.floorandroof.FloorAndRoofDetailsInteractor;
import in.ults.ipms.ui.buildingassets.floorandroof.FloorAndRoofDetailsPresenter;
import in.ults.ipms.ui.buildingassets.floorandroof.IFloorAndRoofDetailsInteractor;
import in.ults.ipms.ui.buildingassets.floorandroof.IFloorAndRoofDetailsPresenter;
import in.ults.ipms.ui.buildingassets.floorandroof.IFloorAndRoofDetailsView;
import in.ults.ipms.ui.buildingassets.home.BuildingAssetHomeInteractor;
import in.ults.ipms.ui.buildingassets.home.BuildingAssetHomePresenter;
import in.ults.ipms.ui.buildingassets.home.IBuildingAssetHomeInteractor;
import in.ults.ipms.ui.buildingassets.home.IBuildingAssetHomePresenter;
import in.ults.ipms.ui.buildingassets.home.IBuildingAssetHomeView;
import in.ults.ipms.ui.buildingassets.location.ILocationDetailsInteractor;
import in.ults.ipms.ui.buildingassets.location.ILocationDetailsPresenter;
import in.ults.ipms.ui.buildingassets.location.ILocationDetailsView;
import in.ults.ipms.ui.buildingassets.location.LocationDetailsInteractor;
import in.ults.ipms.ui.buildingassets.location.LocationDetailsPresenter;
import in.ults.ipms.ui.buildingassets.member.IMemberDetailsInteractor;
import in.ults.ipms.ui.buildingassets.member.IMemberDetailsPresenter;
import in.ults.ipms.ui.buildingassets.member.IMemberDetailsView;
import in.ults.ipms.ui.buildingassets.member.MemberDetailsInteractor;
import in.ults.ipms.ui.buildingassets.member.MemberDetailsPresenter;
import in.ults.ipms.ui.buildingassets.memberlist.IMemberListInteractor;
import in.ults.ipms.ui.buildingassets.memberlist.IMemberListPresenter;
import in.ults.ipms.ui.buildingassets.memberlist.IMemberListView;
import in.ults.ipms.ui.buildingassets.memberlist.MemberListInteractor;
import in.ults.ipms.ui.buildingassets.memberlist.MemberListPresenter;
import in.ults.ipms.ui.buildingassets.otherproperty.IPropertyOtherDetailsInteractor;
import in.ults.ipms.ui.buildingassets.otherproperty.IPropertyOtherDetailsPresenter;
import in.ults.ipms.ui.buildingassets.otherproperty.IPropertyOtherDetailsView;
import in.ults.ipms.ui.buildingassets.otherproperty.PropertyOtherDetailsInteractor;
import in.ults.ipms.ui.buildingassets.otherproperty.PropertyOtherDetailsPresenter;
import in.ults.ipms.ui.buildingassets.owner.IOwnerDetailsInteractor;
import in.ults.ipms.ui.buildingassets.owner.IOwnerDetailsPresenter;
import in.ults.ipms.ui.buildingassets.owner.IOwnerDetailsView;
import in.ults.ipms.ui.buildingassets.owner.OwnerDetailsInteractor;
import in.ults.ipms.ui.buildingassets.owner.OwnerDetailsPresenter;
import in.ults.ipms.ui.buildingassets.ownerlist.IOwnerListInteractor;
import in.ults.ipms.ui.buildingassets.ownerlist.IOwnerListPresenter;
import in.ults.ipms.ui.buildingassets.ownerlist.IOwnerListView;
import in.ults.ipms.ui.buildingassets.ownerlist.OwnerListInteractor;
import in.ults.ipms.ui.buildingassets.ownerlist.OwnerListPresenter;
import in.ults.ipms.ui.buildingassets.property.IPropertyDetailsInteractor;
import in.ults.ipms.ui.buildingassets.property.IPropertyDetailsPresenter;
import in.ults.ipms.ui.buildingassets.property.IPropertyDetailsView;
import in.ults.ipms.ui.buildingassets.property.PropertyDetailsInteractor;
import in.ults.ipms.ui.buildingassets.property.PropertyDetailsPresenter;
import in.ults.ipms.ui.buildingassets.survey.ISurveyDetailsInteractor;
import in.ults.ipms.ui.buildingassets.survey.ISurveyDetailsPresenter;
import in.ults.ipms.ui.buildingassets.survey.ISurveyDetailsView;
import in.ults.ipms.ui.buildingassets.survey.SurveyDetailsInteractor;
import in.ults.ipms.ui.buildingassets.survey.SurveyDetailsPresenter;
import in.ults.ipms.ui.buildingassets.tax.ITaxDetailsInteractor;
import in.ults.ipms.ui.buildingassets.tax.ITaxDetailsPresenter;
import in.ults.ipms.ui.buildingassets.tax.ITaxDetailsView;
import in.ults.ipms.ui.buildingassets.tax.TaxDetailsInteractor;
import in.ults.ipms.ui.buildingassets.tax.TaxDetailsPresenter;
import in.ults.ipms.ui.buildingassets.taxlist.ITaxListInteractor;
import in.ults.ipms.ui.buildingassets.taxlist.ITaxListPresenter;
import in.ults.ipms.ui.buildingassets.taxlist.ITaxListView;
import in.ults.ipms.ui.buildingassets.taxlist.TaxListInteractor;
import in.ults.ipms.ui.buildingassets.taxlist.TaxListPresenter;
import in.ults.ipms.ui.buildingassets.tenant.ITenantDetailsInteractor;
import in.ults.ipms.ui.buildingassets.tenant.ITenantDetailsPresenter;
import in.ults.ipms.ui.buildingassets.tenant.ITenantDetailsView;
import in.ults.ipms.ui.buildingassets.tenant.TenantDetailsInteractor;
import in.ults.ipms.ui.buildingassets.tenant.TenantDetailsPresenter;
import in.ults.ipms.ui.utility.bridge.BridgeDetailsInteractor;
import in.ults.ipms.ui.utility.bridge.BridgeDetailsPresenter;
import in.ults.ipms.ui.utility.bridge.IBridgeDetailsInteractor;
import in.ults.ipms.ui.utility.bridge.IBridgeDetailsPresenter;
import in.ults.ipms.ui.utility.bridge.IBridgeDetailsView;
import in.ults.ipms.ui.utility.culvert.CulvertDetailsInteractor;
import in.ults.ipms.ui.utility.culvert.CulvertDetailsPresenter;
import in.ults.ipms.ui.utility.culvert.ICulvertDetailsInteractor;
import in.ults.ipms.ui.utility.culvert.ICulvertDetailsPresenter;
import in.ults.ipms.ui.utility.culvert.ICulvertDetailsView;
import in.ults.ipms.ui.utility.divider.DividerDetailsInteractor;
import in.ults.ipms.ui.utility.divider.DividerDetailsPresenter;
import in.ults.ipms.ui.utility.divider.IDividerDetailsInteractor;
import in.ults.ipms.ui.utility.divider.IDividerDetailsPresenter;
import in.ults.ipms.ui.utility.divider.IDividerDetailsView;
import in.ults.ipms.ui.utility.drainage.details.DrainageDetailsInteractor;
import in.ults.ipms.ui.utility.drainage.details.DrainageDetailsPresenter;
import in.ults.ipms.ui.utility.drainage.details.IDrainageDetailsInteractor;
import in.ults.ipms.ui.utility.drainage.details.IDrainageDetailsPresenter;
import in.ults.ipms.ui.utility.drainage.details.IDrainageDetailsView;
import in.ults.ipms.ui.utility.drainage.map.DrainageMapInteractor;
import in.ults.ipms.ui.utility.drainage.map.DrainageMapPresenter;
import in.ults.ipms.ui.utility.drainage.map.IDrainageMapInteractor;
import in.ults.ipms.ui.utility.drainage.map.IDrainageMapPresenter;
import in.ults.ipms.ui.utility.drainage.map.IDrainageMapView;
import in.ults.ipms.ui.utility.home.IUtilityHomeInteractor;
import in.ults.ipms.ui.utility.home.IUtilityHomePresenter;
import in.ults.ipms.ui.utility.home.IUtilityHomeView;
import in.ults.ipms.ui.utility.home.UtilityHomeInteractor;
import in.ults.ipms.ui.utility.home.UtilityHomePresenter;
import in.ults.ipms.ui.utility.hump.IRoadHumpDetailsInteractor;
import in.ults.ipms.ui.utility.hump.IRoadHumpDetailsPresenter;
import in.ults.ipms.ui.utility.hump.IRoadHumpDetailsView;
import in.ults.ipms.ui.utility.hump.RoadHumpDetailsInteractor;
import in.ults.ipms.ui.utility.hump.RoadHumpDetailsPresenter;
import in.ults.ipms.ui.utility.junction.IRoadJunctionDetailsInteractor;
import in.ults.ipms.ui.utility.junction.IRoadJunctionDetailsPresenter;
import in.ults.ipms.ui.utility.junction.IRoadJunctionDetailsView;
import in.ults.ipms.ui.utility.junction.RoadJunctionDetailsInteractor;
import in.ults.ipms.ui.utility.junction.RoadJunctionDetailsPresenter;
import in.ults.ipms.ui.utility.parking.IParkingDetailsInteractor;
import in.ults.ipms.ui.utility.parking.IParkingDetailsPresenter;
import in.ults.ipms.ui.utility.parking.IParkingDetailsView;
import in.ults.ipms.ui.utility.parking.ParkingDetailsInteractor;
import in.ults.ipms.ui.utility.parking.ParkingDetailsPresenter;
import in.ults.ipms.ui.utility.playground.IPlaygroundDetailsInteractor;
import in.ults.ipms.ui.utility.playground.IPlaygroundDetailsPresenter;
import in.ults.ipms.ui.utility.playground.IPlaygroundDetailsView;
import in.ults.ipms.ui.utility.playground.PlaygroundDetailsInteractor;
import in.ults.ipms.ui.utility.playground.PlaygroundDetailsPresenter;
import in.ults.ipms.ui.utility.road.details.IRoadDetailsInteractor;
import in.ults.ipms.ui.utility.road.details.IRoadDetailsPresenter;
import in.ults.ipms.ui.utility.road.details.IRoadDetailsView;
import in.ults.ipms.ui.utility.road.details.RoadDetailsInteractor;
import in.ults.ipms.ui.utility.road.details.RoadDetailsPresenter;
import in.ults.ipms.ui.utility.road.map.IRoadMapInteractor;
import in.ults.ipms.ui.utility.road.map.IRoadMapPresenter;
import in.ults.ipms.ui.utility.road.map.IRoadMapView;
import in.ults.ipms.ui.utility.road.map.RoadMapInteractor;
import in.ults.ipms.ui.utility.road.map.RoadMapPresenter;
import in.ults.ipms.ui.utility.signboard.IRoadSignboardDetailsInteractor;
import in.ults.ipms.ui.utility.signboard.IRoadSignboardDetailsPresenter;
import in.ults.ipms.ui.utility.signboard.IRoadSignboardDetailsView;
import in.ults.ipms.ui.utility.signboard.RoadSignboardDetailsInteractor;
import in.ults.ipms.ui.utility.signboard.RoadSignboardDetailsPresenter;
import in.ults.ipms.ui.waterbody.home.IWaterBodyHomeInteractor;
import in.ults.ipms.ui.waterbody.home.IWaterBodyHomePresenter;
import in.ults.ipms.ui.waterbody.home.IWaterBodyHomeView;
import in.ults.ipms.ui.waterbody.home.WaterBodyHomeInteractor;
import in.ults.ipms.ui.waterbody.home.WaterBodyHomePresenter;
import in.ults.ipms.ui.waterbody.pond.IPondDetailsInteractor;
import in.ults.ipms.ui.waterbody.pond.IPondDetailsPresenter;
import in.ults.ipms.ui.waterbody.pond.IPondDetailsView;
import in.ults.ipms.ui.waterbody.pond.PondDetailsInteractor;
import in.ults.ipms.ui.waterbody.pond.PondDetailsPresenter;
import in.ults.ipms.utils.rx.AppSchedulerProvider;
import in.ults.ipms.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed Shafeeq on 3/20/2018.
 */

@Module
public class FragmentModule {

    private final BaseActivity baseActivity;

    public FragmentModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    CompositeDisposable providesCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }

    @Provides
    @PerFragment
    ILoginPresenter<ILoginView, ILoginInteractor> provideLoginFP(LoginPresenter<ILoginView, ILoginInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ILoginInteractor provideLoginFI(LoginInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IForgotPasswordPresenter<IForgotPasswordView, IForgotPasswordInteractor> provideForgotPasswordFP(ForgotPasswordPresenter<IForgotPasswordView, IForgotPasswordInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IForgotPasswordInteractor provideForgotPasswordFI(ForgotPasswordInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IBuildingDetailsPresenter<IBuildingDetailsView, IBuildingDetailsInteractor> provideBuildingDetailsFP(BuildingDetailsPresenter<IBuildingDetailsView, IBuildingDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IBuildingDetailsInteractor provideBuildingDetailsFI(BuildingDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IPropertyDetailsPresenter<IPropertyDetailsView, IPropertyDetailsInteractor> providePropertyDetailsFP(PropertyDetailsPresenter<IPropertyDetailsView, IPropertyDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IPropertyDetailsInteractor providePropertyDetailsFI(PropertyDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IOwnerDetailsPresenter<IOwnerDetailsView, IOwnerDetailsInteractor> provideOwnerDetailsFP(OwnerDetailsPresenter<IOwnerDetailsView, IOwnerDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IOwnerDetailsInteractor provideOwnerDetailsFI(OwnerDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IMemberDetailsPresenter<IMemberDetailsView, IMemberDetailsInteractor> provideMemberDetailsFP(MemberDetailsPresenter<IMemberDetailsView, IMemberDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IMemberDetailsInteractor provideMemberDetailsFI(MemberDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ITenantDetailsPresenter<ITenantDetailsView, ITenantDetailsInteractor> provideTenantDetailsFP(TenantDetailsPresenter<ITenantDetailsView, ITenantDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ITenantDetailsInteractor provideTenantDetailsFI(TenantDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ISurveyDetailsPresenter<ISurveyDetailsView, ISurveyDetailsInteractor> provideSurveyDetailsFP(SurveyDetailsPresenter<ISurveyDetailsView, ISurveyDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ISurveyDetailsInteractor provideSurveyDetailsFI(SurveyDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ITaxDetailsPresenter<ITaxDetailsView, ITaxDetailsInteractor> provideTaxDetailsFP(TaxDetailsPresenter<ITaxDetailsView, ITaxDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ITaxDetailsInteractor provideTaxDetailsFI(TaxDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IEstablishmentDetailsPresenter<IEstablishmentDetailsView, IEstablishmentDetailsInteractor> provideEstablishmentDetailsFP(EstablishmentDetailsPresenter<IEstablishmentDetailsView, IEstablishmentDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IEstablishmentDetailsInteractor provideEstablishmentDetailsFI(EstablishmentDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IPropertyOtherDetailsPresenter<IPropertyOtherDetailsView, IPropertyOtherDetailsInteractor> providePropertyOtherDetailsFP(PropertyOtherDetailsPresenter<IPropertyOtherDetailsView, IPropertyOtherDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IPropertyOtherDetailsInteractor providePropertyOtherDetailsFI(PropertyOtherDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ILocationDetailsPresenter<ILocationDetailsView, ILocationDetailsInteractor> provideLocationDetailsFP(LocationDetailsPresenter<ILocationDetailsView, ILocationDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ILocationDetailsInteractor provideLocationDetailsFI(LocationDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    in.ults.ipms.ui.buildingassets.road.IRoadDetailsPresenter<in.ults.ipms.ui.buildingassets.road.IRoadDetailsView, in.ults.ipms.ui.buildingassets.road.IRoadDetailsInteractor> provideRoadDetailsFP(in.ults.ipms.ui.buildingassets.road.RoadDetailsPresenter<in.ults.ipms.ui.buildingassets.road.IRoadDetailsView, in.ults.ipms.ui.buildingassets.road.IRoadDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    in.ults.ipms.ui.buildingassets.road.IRoadDetailsInteractor provideRoadDetailsFI(in.ults.ipms.ui.buildingassets.road.RoadDetailsInteractor interactor) {
        return interactor;
    }
    @Provides
    @PerFragment
    IBuildingAssetHomePresenter<IBuildingAssetHomeView, IBuildingAssetHomeInteractor> provideBuildingAssetHomeFP(BuildingAssetHomePresenter<IBuildingAssetHomeView, IBuildingAssetHomeInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IBuildingAssetHomeInteractor provideBuildingAssetHomeFI(BuildingAssetHomeInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    BuildingAssetHomeAdapter provideBuildingAssetHomeAdapter() {
        return new BuildingAssetHomeAdapter();
    }

    @Provides
    @PerFragment
    MembersListAdapter provideMembersListAdapter() {
        return new MembersListAdapter();
    }

    @Provides
    @PerFragment
    LinearLayoutManager provideLinearLayoutManager(BaseActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerFragment
    GridLayoutManager provideGridLayoutManager(BaseActivity activity) {
        return new GridLayoutManager(activity, 2);
    }


    @Provides
    @PerFragment
    IMemberListPresenter<IMemberListView, IMemberListInteractor> provideMemberListFP(MemberListPresenter<IMemberListView, IMemberListInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IMemberListInteractor provideMemberListFI(MemberListInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    TaxListAdapter provideTaxListAdapter() {
        return new TaxListAdapter();
    }


    @Provides
    @PerFragment
    IOwnerListPresenter<IOwnerListView, IOwnerListInteractor> provideOwnerListFP(OwnerListPresenter<IOwnerListView, IOwnerListInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IOwnerListInteractor provideOwnerListFI(OwnerListInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ITaxListPresenter<ITaxListView, ITaxListInteractor> provideTaxListFP(TaxListPresenter<ITaxListView, ITaxListInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ITaxListInteractor provideTaxListFI(TaxListInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    OwnerListAdapter provideOwnerListAdapter() {
        return new OwnerListAdapter();
    }


    @Provides
    @PerFragment
    IRoadMapPresenter<IRoadMapView, IRoadMapInteractor> provideRoadMapFP(RoadMapPresenter<IRoadMapView, IRoadMapInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IRoadMapInteractor provideRoadMapFI(RoadMapInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IRoadDetailsPresenter<IRoadDetailsView, IRoadDetailsInteractor> provideAddRoadFP(RoadDetailsPresenter<IRoadDetailsView, IRoadDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IRoadDetailsInteractor provideAddRoadFI(RoadDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    FloorAreaAdapter provideFloorAreaAdapter(BaseActivity activity) {
        return new FloorAreaAdapter(activity);
    }

    @Provides
    @PerFragment
    RoofTypeAdapter provideRoofTypeAdapter(BaseActivity activity) {
        return new RoofTypeAdapter(activity);
    }


    @Provides
    @PerFragment
    FloorTypeAdapter provideFloorTypeAdapter(BaseActivity activity) {
        return new FloorTypeAdapter();
    }

    @Provides
    @PerFragment
    DisabilityAdapter provideDisabilityAdapter(BaseActivity activity) {
        return new DisabilityAdapter(activity);
    }


    @Provides
    @PerFragment
    IUtilityHomePresenter<IUtilityHomeView, IUtilityHomeInteractor> provideUtilityHomeFP(UtilityHomePresenter<IUtilityHomeView, IUtilityHomeInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IUtilityHomeInteractor provideUtilityHomeFI(UtilityHomeInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    UtilityHomeAdapter provideUtilityHomeAdapter() {
        return new UtilityHomeAdapter();
    }


    @Provides
    @PerFragment
    WaterBodyHomeAdapter provideWaterBodyHomeAdapter() {
        return new WaterBodyHomeAdapter();
    }


    @Provides
    @PerFragment
    IBridgeDetailsPresenter<IBridgeDetailsView, IBridgeDetailsInteractor> provideBridgeFP(BridgeDetailsPresenter<IBridgeDetailsView, IBridgeDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IBridgeDetailsInteractor provideBridgeFI(BridgeDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    ICulvertDetailsPresenter<ICulvertDetailsView, ICulvertDetailsInteractor> provideCulvertFP(CulvertDetailsPresenter<ICulvertDetailsView, ICulvertDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    ICulvertDetailsInteractor provideCulvertFI(CulvertDetailsInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerFragment
    IDividerDetailsPresenter<IDividerDetailsView, IDividerDetailsInteractor> provideDividerFP(DividerDetailsPresenter<IDividerDetailsView, IDividerDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IDividerDetailsInteractor provideDividerFI(DividerDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IParkingDetailsPresenter<IParkingDetailsView, IParkingDetailsInteractor> provideParkingFP(ParkingDetailsPresenter<IParkingDetailsView, IParkingDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IParkingDetailsInteractor provideParkingFI(ParkingDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IPlaygroundDetailsPresenter<IPlaygroundDetailsView, IPlaygroundDetailsInteractor> providePlaygroundFP(PlaygroundDetailsPresenter<IPlaygroundDetailsView, IPlaygroundDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IPlaygroundDetailsInteractor providePlaygroundFI(PlaygroundDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IRoadHumpDetailsPresenter<IRoadHumpDetailsView, IRoadHumpDetailsInteractor> provideRoadHumpFP(RoadHumpDetailsPresenter<IRoadHumpDetailsView, IRoadHumpDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IRoadHumpDetailsInteractor provideRoadHumpFI(RoadHumpDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IRoadJunctionDetailsPresenter<IRoadJunctionDetailsView, IRoadJunctionDetailsInteractor> provideRoadJunctionFP(RoadJunctionDetailsPresenter<IRoadJunctionDetailsView, IRoadJunctionDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IRoadJunctionDetailsInteractor provideRoadJunctionFI(RoadJunctionDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IRoadSignboardDetailsPresenter<IRoadSignboardDetailsView, IRoadSignboardDetailsInteractor> provideRoadSignboardFP(RoadSignboardDetailsPresenter<IRoadSignboardDetailsView, IRoadSignboardDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IRoadSignboardDetailsInteractor provideRoadSignboardFI(RoadSignboardDetailsInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerFragment
    IDrainageMapPresenter<IDrainageMapView, IDrainageMapInteractor> provideDrainageMapFP(DrainageMapPresenter<IDrainageMapView, IDrainageMapInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IDrainageMapInteractor provideDrainageMapFI(DrainageMapInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerFragment
    IDrainageDetailsPresenter<IDrainageDetailsView, IDrainageDetailsInteractor> provideDrainageDetailsFP(DrainageDetailsPresenter<IDrainageDetailsView, IDrainageDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IDrainageDetailsInteractor provideDrainageDetailsFI(DrainageDetailsInteractor interactor) {
        return interactor;
    }


    @Provides
    @PerFragment
    IPondDetailsPresenter<IPondDetailsView, IPondDetailsInteractor> providePondDetailsFP(PondDetailsPresenter<IPondDetailsView, IPondDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IPondDetailsInteractor providePondDetailsFI(PondDetailsInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IWaterBodyHomePresenter<IWaterBodyHomeView, IWaterBodyHomeInteractor> provideWaterBodyHomeDetailsFP(WaterBodyHomePresenter<IWaterBodyHomeView, IWaterBodyHomeInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IWaterBodyHomeInteractor provideWaterBodyHomeDetailsFI(WaterBodyHomeInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerFragment
    IFloorAndRoofDetailsPresenter<IFloorAndRoofDetailsView, IFloorAndRoofDetailsInteractor> provideFloorAndRoofDetailsFP(FloorAndRoofDetailsPresenter<IFloorAndRoofDetailsView, IFloorAndRoofDetailsInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    IFloorAndRoofDetailsInteractor provideFloorAndRoofDetailsFI(FloorAndRoofDetailsInteractor interactor) {
        return interactor;
    }
}
