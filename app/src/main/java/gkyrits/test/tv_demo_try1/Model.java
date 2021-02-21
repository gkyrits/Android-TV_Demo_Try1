package gkyrits.test.tv_demo_try1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public static final int TYPE_TEXT=0;
    public static final int TYPE_IMAGE=1;
    public static final int TYPE_CARD=2;
    public List<Row> rows=null;

    //--------------------------------------
    public static class Element implements Serializable {
        private final int type;
        private final String title;
        private final String description;
        private final String image;
        public String videoPath;

        public Element(int type, String title, String description, String image) {
            this.type = type;
            this.title = title;
            this.description = description;
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getImage() {
            return image;
        }
    }

    //--------------------------------------
    public class Row{
        private final String title;
        private final String description;
        List<Element> elements=null;

        public Row(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public void add(Element elem){
            if(elements==null)
                elements = new ArrayList<>();
            elements.add(elem);
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public List<Element> getElements() {
            return elements;
        }
    }

    //--------------------------------------
    private static final int ROWS=2;
    private static final int ELEMS=4;
    private final int[] types = {TYPE_TEXT,TYPE_IMAGE};
    private final String[] head_titles = {"Texts","Icons"};
    private final String[] head_descr  = {"Row of Text","Row of Icon"};

    public void initialize(){
        rows = new ArrayList<>();
        for(int rr=0; rr<ROWS; rr++){
            Row row = new Row(head_titles[rr],head_descr[rr]);
            row.elements = new ArrayList<>();
            for(int cc=0; cc<ELEMS; cc++){
                Element elem = new Element(types[rr],"text_"+rr+"_"+cc,"Text item","");
                row.elements.add(elem);
            }
            rows.add(row);
        }
    }

}
