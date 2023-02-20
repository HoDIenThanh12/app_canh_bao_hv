package com.example.tiki.app_canhbao.viewmodel;

import static com.example.tiki.app_canhbao.constants.INSTANT.PATH_DTAREATIME_USER;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.Meettings;
import com.example.tiki.app_canhbao.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListUserMeettingViewModel extends ViewModel {
    private boolean isLoad;
    private MutableLiveData<List<User>> mListUserMeetting =new MutableLiveData<>();
    private Meettings mMeetting;
    private MutableLiveData<Meettings> mMutableLiveMeting=new MutableLiveData<>();
    private ListUserMetingFactory listUserMetingFactory;

    public ListUserMeettingViewModel() {
        super();
        this.mListUserMeetting=new MutableLiveData<>();
        this.mMutableLiveMeting=new MutableLiveData<>();
        //Log.e("user--> ","khởi tạo vm: " + getmMeetting().get_id());

        //getAllUser("132");
        //Log.d("khởi tạo có factory -->>> ",""+this.getmMeetting().get_id());
    }

    public ListUserMeettingViewModel(ListUserMetingFactory listUserMetingFactory) {
        this.listUserMetingFactory = listUserMetingFactory;
        //getAllUser("123");
        //this.mListUserMeetting=new MutableLiveData<>();
        Log.d("khởi tạo có factory -->>> ","");
    }

    public ListUserMeettingViewModel(Meettings mMeetting) {
        //this.mListUserMeetting=new MutableLiveData<>();
        this.mMeetting=mMeetting;
        this.mMutableLiveMeting.setValue(mMeetting);
        Log.e("đối tương factory-- > ", " "+ mMeetting.get_nameMeet());
        //getAllUser(mMeetting.get_id());
    }

    public ListUserMeettingViewModel(MutableLiveData<Meettings> mMutableLiveMeting) {
        this.mMutableLiveMeting = mMutableLiveMeting;
    }

    ///xử lý logic////////////////////
    public void getAllUser(String id){
        List <User> lUser=new ArrayList<>();
        lUser.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference(PATH_DTAREATIME_USER);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot==null){
                    Log.e("ds --> ","  chư quét đc dữ liệu");
                }
                else {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        User utemp =dataSnapshot.getValue(User.class);
                        if(!utemp.get_id().equals(AuthAccount.getInstant().userAccount.get_id())){
                            lUser.add(utemp);
                        }
                    }
                    addAllLiist(lUser);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Log.e("ds --> ","  "+lUser.);
//        for(int i=0;i<5;i++){
//            lUser.add(new User("123","123","123",1));
//        }
    }
    private void getUserSelect(String id){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("");
    }
    private void addAllLiist(List<User> lUser){
        mListUserMeetting.setValue(lUser);
        //Log.e("user--> ",""+lUser.size());
    }
    //////////////////
    public MutableLiveData<List<User>> getmListUserMeetting() {
        return mListUserMeetting;
    }

    public Meettings getmMeetting() {
        return mMeetting;
    }

    public void setmMeetting(Meettings mMeetting) {
        this.mMeetting = mMeetting;
    }

    public MutableLiveData<Meettings> getmMutableLiveMeting() {
        return mMutableLiveMeting;
    }

    public void setmMutableLiveMeting(MutableLiveData<Meettings> mMutableLiveMeting) {
        this.mMutableLiveMeting = mMutableLiveMeting;
    }

    public ListUserMetingFactory getListUserMetingFactory() {
        return listUserMetingFactory;
    }

    public void setListUserMetingFactory(ListUserMetingFactory listUserMetingFactory) {
        this.listUserMetingFactory = listUserMetingFactory;
        getAllUser(listUserMetingFactory.getmFactory().get_id());
    }
}
