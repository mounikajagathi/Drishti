package in.ults.ipms.ui.buildingassets.member;

import java.util.ArrayList;

import in.ults.ipms.adapters.DisabilityAdapter;
import in.ults.ipms.ui.base.IBasePresenter;

public interface IMemberDetailsPresenter <V extends IMemberDetailsView, I extends IMemberDetailsInteractor> extends IBasePresenter<V,I> {

    void validateData(String firstName, String lastName, String gender, String age, boolean isMonth, String memberOccupation, String bloodGroup,String memberDOB,String religion,String caste, String educationCategory, String education, String maritalStatus, String isCoolieWage, String isAlive, String isQualified, String bankAccount, String nriOrNrk, ArrayList<String> bankType, ArrayList<String> pension, ArrayList<String> disease, DisabilityAdapter disabilityAdapter);
}
