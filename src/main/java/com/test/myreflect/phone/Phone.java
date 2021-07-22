package com.test.myreflect.phone;

import com.test.myreflect.phone.ms.MobieService;
import com.test.myreflect.phone.os.OperateSystem;

public class Phone {
    private String phoneName;
    private MobieService ms;
    private OperateSystem os;
    public void showMS_OS()
    {
        System.out.print(phoneName+" : "+ms.showMSName()+" + "+os.showOSName());
    }

    Phone(){}
    Phone(String phoneName){
        this.phoneName = phoneName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public MobieService getMs() {
        return ms;
    }

    public void setMs(MobieService ms) {
        this.ms = ms;
    }

    public OperateSystem getOs() {
        return os;
    }

    public void setOs(OperateSystem os) {
        this.os = os;
    }
}
