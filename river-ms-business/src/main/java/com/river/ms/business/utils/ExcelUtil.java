package com.river.ms.business.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

import com.river.core.annotation.DataInput;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * 
 * @author zyy
 *
 */
public class ExcelUtil {
	/**
	 * 获取当前cell的值
	 * 
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCellFormatValue(Cell cell) {
		String cellValue = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:// 字符串类型
			cellValue = cell.getStringCellValue();
			if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
				cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: // 数值类型
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellValue;
	}

	public static Field getField(String value, Class c) {
		Field[] declaredFields = c.getDeclaredFields();
		Field field = null;
		for (Field f : declaredFields) {
			DataInput dataInput = f.getAnnotation(DataInput.class);
			if (dataInput != null) {
				String columnName = dataInput.columnName();
				if (columnName.equals(value)) {
					field = f;
				}
			}
		}
		return field;
	}

	public static void setValue(Object en, Field field
			, String value, Class clazz) throws Exception {
		if (field == null) {
			return;
		} else {
			 String name = field.getName();
			 Class type = field.getType();
			 Method method = null;
             try {
                //反射得到javaBean每个字段的set方法
                method = clazz.getMethod("set" + name.replaceFirst(name.substring(0, 1),
                        name.substring(0, 1).toUpperCase()), type);
                method.invoke(en, ConvertUtils.convert(value, type));
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Import excel read error!");
            }
		}
	}

}
