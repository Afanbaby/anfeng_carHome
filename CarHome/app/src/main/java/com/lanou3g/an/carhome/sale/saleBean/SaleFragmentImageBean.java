package com.lanou3g.an.carhome.sale.saleBean;

import java.util.List;

/**
 * Created by anfeng on 16/5/21.
 */
public class SaleFragmentImageBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"id":6344,"title":"20160521","shorttitle":"开放平台专题","url":"http://topic.m.autohome.com.cn/mall/2016/5/special/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g19/M08/64/C4/autohomecar__wKjBxFc9YtGAL-u9AAIAk8eGxLY398.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":6346,"title":"20160521","shorttitle":"标致2008","url":"http://m.mall.autohome.com.cn/detail/85631-110100-0.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g7/M05/82/AB/autohomecar__wKgHzlc9ZCiAPA3JAAI2JomFnDE325.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":6352,"title":"521","shorttitle":"零首付分期新","url":"http://topic.m.autohome.com.cn/mall/2016/5/1/index.html#pvareaid=106574","imgurl":"http://app2.autoimg.cn/appdfs/g23/M11/66/2A/autohomecar__wKgFV1c-fv-AbENyAAEE6T9Q3aU231.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":6350,"title":"20160521","shorttitle":"北汽幻速S2创业版","url":"http://topic.m.autohome.com.cn/mall/2016/5/huansumay/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g23/M14/65/A9/autohomecar__wKgFXFc9gfSAYZ51AAJPf5o5qnU239.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":6348,"title":"20160521","shorttitle":"吉利金刚","url":"http://topic.m.autohome.com.cn/mall/2016/5/prize-quiz/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g18/M13/82/CD/autohomecar__wKgH2Vc9gV-AGgxMAAL75j4GXgg171.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 6344
         * title : 20160521
         * shorttitle : 开放平台专题
         * url : http://topic.m.autohome.com.cn/mall/2016/5/special/#pvareaid=104735
         * imgurl : http://app2.autoimg.cn/appdfs/g19/M08/64/C4/autohomecar__wKjBxFc9YtGAL-u9AAIAk8eGxLY398.jpg
         * urlscheme :
         * type : 2
         * appicon :
         * siteindex : 0
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String shorttitle;
            private String url;
            private String imgurl;
            private String urlscheme;
            private int type;
            private String appicon;
            private int siteindex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShorttitle() {
                return shorttitle;
            }

            public void setShorttitle(String shorttitle) {
                this.shorttitle = shorttitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getUrlscheme() {
                return urlscheme;
            }

            public void setUrlscheme(String urlscheme) {
                this.urlscheme = urlscheme;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAppicon() {
                return appicon;
            }

            public void setAppicon(String appicon) {
                this.appicon = appicon;
            }

            public int getSiteindex() {
                return siteindex;
            }

            public void setSiteindex(int siteindex) {
                this.siteindex = siteindex;
            }
        }
    }
}
