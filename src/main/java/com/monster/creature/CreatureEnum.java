package com.monster.creature;/**
 * @author Administrator
 * @Date 2019/6/17
 * @see
 */

/**
 *<p>Title:CreatureEnum</p>
 *<p>Description:</p>
 *<p>Company:wegoo</p>
 *
 *@author hujian
 *@version 1.0
 *@date 2019/6/17 9:54
 *
 */
public enum CreatureEnum {
       Propertie("base","creature.properties"),
       EntityPath("entityPath","entityPath"),
       CreatureAno("CreatureAno","@CreatureAno"),
       Leaf("leafPathIn","leafPathOut"),
       Controller("controllerPathIn","controllerPathOut"),
       Service("servicePathIn","servicePathOut"),
       ServiceImpl("serviceImplPathIn","serviceImplPathOut"),
       Repository("repositoryPathIn","repositoryPathOut"),
       Search("searchPathIn","searchPathOut"),
       Vo("voPathIn","voPathOut"),
        Java("java",".java"),
        Html("html",".html"),
        PathSplitFag("target","target");

        CreatureEnum(String name, String value) {
                this.name = name;
                this.value = value;
        }

        private String value;
        private String name;

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }


}
