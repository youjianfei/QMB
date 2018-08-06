package com.jingnuo.quanmb.entityclass;

import java.util.List;

public class ShouyeRadios {

    /**
     * code : 1
     * data : [{"content":"附近帮手已经完成空调维修任务，获得佣金300元","id":1},{"content":"附近帮手已经完成手机维修任务，获得佣金280元","id":2},{"content":"附近商户已经完成家庭保洁任务，获得佣金100元","id":3},{"content":"附近商户已经完成空调维修任务，获得佣金175元","id":4},{"content":"附近帮手已经完成代驾任务，获得佣金80元","id":5},{"content":"附近帮手已经完成搬运任务，获得佣金100元","id":6},{"content":"附近商户已经完成车辆维修任务，获得佣金1700元","id":7},{"content":"附近帮手已经完成帮买帮送维修任务，获得佣金55.55元","id":8},{"content":"附近商户已经完成空调维修任务，获得佣金233元","id":9},{"content":"附近商户已经完成司仪主持任务，获得佣金1000元","id":10},{"content":"附近商户已经完成宠物护理任务，获得佣金100元","id":11},{"content":"附近帮手已经完成家庭保洁任务，获得佣金60元","id":12},{"content":"附近帮手已经完成预约排队任务，获得佣金15元","id":13},{"content":"附近商户已经完成手机维修任务，获得佣金190元","id":14},{"content":"附近帮手已经完成帮买帮送任务，获得佣金67.50元","id":15},{"content":"附近帮手已经完成私人订制任务，获得佣金300元","id":16},{"content":"附近商户已经完成汽车保养任务，获得佣金600元","id":17},{"content":"附近商户已经完成租车任务，获得佣金180元","id":18},{"content":"附近商户已经完成空调维修任务，获得佣金230元","id":19},{"content":"附近商户已经完成送水任务，获得佣金40元","id":20},{"content":"附近帮手已经完成帮买帮送任务，获得佣金125元","id":21},{"content":"附近商户已经完成中型货车运输任务，获得佣金680元","id":22},{"content":"附近帮手已经完成家庭保洁任务，获得佣金50元","id":23},{"content":"附近商户已经完成搬家任务，获得佣金150元","id":24},{"content":"附近帮手已经完成帮买帮送任务，获得佣金100元","id":25},{"content":"附近商户已经完成空调维修任务，获得佣金280元","id":26},{"content":"附近帮手已经完成数码维修任务，获得佣金500元","id":27},{"content":"附近商户已经完成电脑维修任务，获得佣金200元","id":28},{"content":"附近商户已经完成空调维修任务，获得佣金300元","id":29},{"content":"附近商户已经完成空调维修任务，获得佣金230元","id":30},{"content":"附近帮手已经完成帮买帮送任务，获得佣金35元","id":31},{"content":"附近帮手已经完成家庭保洁任务，获得佣金120元","id":32}]
     * msg : 获取成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 附近帮手已经完成空调维修任务，获得佣金300元
         * id : 1
         */

        private String content;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
