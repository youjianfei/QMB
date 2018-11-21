package com.jingnuo.quanmb.entityclass;

import java.util.List;

/**
 * Created by 飞 on 2018/11/21.
 */

public class JiazhengbiaogeBean {

    /**
     * code : 1
     * data : {"app_taskReferenceForm":[{"table1":"","table2":"价格","table3":"建议面积"},{"table1":"3小时","table2":"120元","table3":"61\u201490㎡"},{"table1":"4小时","table2":"160元","table3":"91-120㎡"}],"description":"服务内容：物品归位整理、无覆盖物地面清洁；内窗清洁、更换垃圾袋；床底、柜底\r\n除尘、家具家电表面清洁；厨卫清理打扫。"}
     * messgae : 操作成功
     */

    private int code;
    private DataBean data;
    private String messgae;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public static class DataBean {
        /**
         * app_taskReferenceForm : [{"table1":"","table2":"价格","table3":"建议面积"},{"table1":"3小时","table2":"120元","table3":"61\u201490㎡"},{"table1":"4小时","table2":"160元","table3":"91-120㎡"}]
         * description : 服务内容：物品归位整理、无覆盖物地面清洁；内窗清洁、更换垃圾袋；床底、柜底
         除尘、家具家电表面清洁；厨卫清理打扫。
         */

        private String description;
        private List<AppTaskReferenceFormBean> app_taskReferenceForm;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<AppTaskReferenceFormBean> getApp_taskReferenceForm() {
            return app_taskReferenceForm;
        }

        public void setApp_taskReferenceForm(List<AppTaskReferenceFormBean> app_taskReferenceForm) {
            this.app_taskReferenceForm = app_taskReferenceForm;
        }

        public static class AppTaskReferenceFormBean {
            /**
             * table1 :
             * table2 : 价格
             * table3 : 建议面积
             */

            private String table1;
            private String table2;
            private String table3;

            public String getTable1() {
                return table1;
            }

            public void setTable1(String table1) {
                this.table1 = table1;
            }

            public String getTable2() {
                return table2;
            }

            public void setTable2(String table2) {
                this.table2 = table2;
            }

            public String getTable3() {
                return table3;
            }

            public void setTable3(String table3) {
                this.table3 = table3;
            }
        }
    }
}
