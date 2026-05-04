
package com.example.db;

import java.sql.Date;

public class ShopOwner {
    private int ShopId;
    private String ShopName;
    private String ShopPhone;
    private String ShopAddress;
    private String ShopEmail;
    private Date ShopDateofbirth;
    public ShopOwner() {
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int shopId) {
        ShopId = shopId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopPhone() {
        return ShopPhone;
    }

    public void setShopPhone(String shopPhone) {
        ShopPhone = shopPhone;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public void setShopAddress(String shopAddress) {
        ShopAddress = shopAddress;
    }

    public String getShopEmail() {
        return ShopEmail;
    }

    public void setShopEmail(String shopEmail) {
        ShopEmail = shopEmail;
    }

    public Date getShopDateofbirth() {
        return ShopDateofbirth;
    }

    public void setShopDateofbirth(Date shopDateofbirth) {
        ShopDateofbirth = shopDateofbirth;
    }
}
