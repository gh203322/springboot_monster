package com.monster.creature;

/**
 * @author Administrator
 *  creature 枚举类
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
