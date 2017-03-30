package red.dargon.com.stickrecycleviewdemo;

import java.io.Serializable;

/**
 * bean
 * Created by Administrator on 2017/3/27.
 */

public class ItemBean implements Serializable {
    private String name;
    private int drawableId;

    public ItemBean(String name, int drawableId) {
        this.name = name;
        this.drawableId = drawableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemBean itemBean = (ItemBean) o;

        if (drawableId != itemBean.drawableId) return false;
        return name != null ? name.equals(itemBean.name) : itemBean.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + drawableId;
        return result;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "name='" + name + '\'' +
                ", drawableId=" + drawableId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
