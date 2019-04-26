package it.firegloves.styles.template;

import org.apache.poi.ss.usermodel.*;

public class AquaStyleTemplate extends HueStyleTemplate {

    private static final short HEADER_CELL_BG_COLOR_INDEX = IndexedColors.SKY_BLUE.getIndex();
    private static final short COMMON_CELL_BG_COLOR_INDEX = IndexedColors.LIGHT_TURQUOISE.getIndex();
    private static final short SUB_FOOTER_CELL_BG_COLOR_INDEX = IndexedColors.AQUA.getIndex();

    public AquaStyleTemplate() {
        super();
        this.setHeaderCellBgColorIndex(HEADER_CELL_BG_COLOR_INDEX);
        this.setCommonCellBgColorIndex(COMMON_CELL_BG_COLOR_INDEX);
        this.setSubFooterCellBgColorIndex(SUB_FOOTER_CELL_BG_COLOR_INDEX);
    }

}
