package csv;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.csvreader.CsvWriter;

public class TestCsv {

	public static void main(String[] args) throws Exception {
		zip();
	}

	public static void zip() throws Exception {
		//需要 格式化的 txt文件，也可以是其他文本，byte[]数组
		FileInputStream fis = new FileInputStream("/Users/liufengyuan/Downloads/asd/bw.txt");
		byte[] buffer = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = fis.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		fis.close();
		bos.close();
		buffer = bos.toByteArray();// 文件的 byte数组
		String str = new String(buffer, "UTF-8");
		String[] strs = str.split("&");
		
		List<String> list = new ArrayList<String>();//zip的集合
		
		String filePath = "/Users/liufengyuan/Downloads/asd/11.csv";
		list.add(filePath);
		File file = new File(filePath);
		file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out, "GBK");
		BufferedWriter bw = new BufferedWriter(osw);
		boolean head = false;// 是否写了头信息
		for (String s : strs) {
			JSONObject obj = JSONObject.fromObject(s);
			System.out.println(obj);
			String json1 = obj.getString("bwData");

			System.out.println(json1);
			@SuppressWarnings("unchecked")
			Map<String,String> map = JSONObject.fromObject(json1.substring(1, json1.length() - 1));

			Iterator<String> it = map.keySet().iterator();
			// 第一次 输入key
			while (it.hasNext() && !head) {
				String key = it.next().toString();
				//System.out.println(key + "--" + value);// 最终得到的结果
				bw.write(key + ",");
			}
			head = true;
			
			// 以后输入 value
			it = map.keySet().iterator();
			bw.write("\r\n");
			while (it.hasNext()) {
				String key = it.next().toString();
				String value = (String) map.get(key);
				System.out.println(key + "--" + value);// 最终得到的结果
				bw.write(value + ",");
			}
		}
		bw.close();
		osw.close();
		out.close();
		
		//target 是 输出的 zip包
		FileOutputStream target = new FileOutputStream("/Users/liufengyuan/Downloads/asd/bw.zip");
		//zipout 是写入 的流
        ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(target));

        //list里是 List<String> list = new ArrayList<String>();  zip的集合
        for (String s : list) {
			File f = new File(s);
			FileInputStream fi = new FileInputStream(f);
			BufferedInputStream origin = new BufferedInputStream(fi);
			ZipEntry entry = new ZipEntry(f.getName());
			zipout.putNextEntry(entry);
			int count;
            while ((count = origin.read(b)) != -1) {
            	zipout.write(b, 0, count);
            }
            origin.close();
		}
        zipout.close();
		
	}

	/**
	 * 写入CSV文件，无追加功能，所以每次都得重新写（包括表头）
	 * 
	 * @author liufengyuan
	 * @date 2017年2月14日 上午9:32:38
	 */
	public static void test1() {
		CsvWriter wr = new CsvWriter("/Users/liufengyuan/Downloads/asd/a.csv", ',', Charset.forName("UTF-8"));
		String[] contents = { "{ \"bwData\":[ { \"aa\":\"111\", \"bb\":\"222\" } ] } " };
		try {
			wr.writeRecord(contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wr.close();
	}

	/**
	 * 生成为CVS文件
	 * 
	 * @param exportData
	 *            源数据List
	 * @param map
	 *            csv文件的列表头map
	 * @param outPutPath
	 *            文件路径
	 * @param fileName
	 *            文件名称
	 */
	public static void test2() throws Exception {

		FileInputStream fis = new FileInputStream("/Users/liufengyuan/Downloads/asd/bw.txt");
		byte[] buffer = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = fis.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		fis.close();
		bos.close();
		buffer = bos.toByteArray();// 文件的 byte数组
		String str = new String(buffer, "UTF-8");
		System.out.println(str);

		List exportData = new ArrayList();
		LinkedHashMap map = new LinkedHashMap();
		String outPutPath = "";
		String fileName = "";

		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			File file = new File(outPutPath);
			if (!file.exists()) {
				file.mkdir();
			}
			// 定义文件名格式并创建
			csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
			System.out.println("csvFile：" + csvFile);
			// UTF-8使正确读取分隔符","
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
			System.out.println("csvFileOutputStream：" + csvFileOutputStream);
			// 写入文件头部
			for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
				java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
				csvFileOutputStream.write("\"" + (String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" + "\"");
				if (propertyIterator.hasNext()) {
					csvFileOutputStream.write(",");
				}
			}
			csvFileOutputStream.newLine();
			// 写入文件内容
			for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
				Object row = (Object) iterator.next();
				for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
					java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
					csvFileOutputStream.write((String) BeanUtils.getProperty(row, (String) propertyEntry.getKey()));
					if (propertyIterator.hasNext()) {
						csvFileOutputStream.write(",");
					}
				}
				if (iterator.hasNext()) {
					csvFileOutputStream.newLine();
				}
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
// class Excel {
// @SuppressWarnings("unchecked")
// // 创建excel文件函数
// // src为待保存的文件路径,json为待保存的json数据
// public static JSONObject createExcel(String src, JSONObject json) {
// JSONObject result = new JSONObject(); // 用来反馈函数调用结果
// try {
// // 新建文件
// File file = new File(src);
// file.createNewFile();
//
// OutputStream outputStream = new FileOutputStream(file);// 创建工作薄
// WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
// WritableSheet sheet = writableWorkbook.createSheet("First sheet", 0);//
// 创建新的一页
//
// JSONArray jsonArray = json.getJSONArray("data");// 得到data对应的JSONArray
// Label label; // 单元格对象
// int column = 0; // 列数计数
//
// // 将第一行信息加到页中。如：姓名、年龄、性别
// JSONObject first = jsonArray.getJSONObject(0);
// Iterator<String> iterator = first.keys(); // 得到第一项的key集合
// while (iterator.hasNext()) { // 遍历key集合
// String key = (String) iterator.next(); // 得到key
// label = new Label(column++, 0, key); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
// sheet.addCell(label); // 将单元格加到页
// }
//
// // 遍历jsonArray
// for (int i = 0; i < jsonArray.size(); i++) {
// JSONObject item = jsonArray.getJSONObject(i); // 得到数组的每项
// iterator = item.keys(); // 得到key集合
// column = 0;// 从第0列开始放
// while (iterator.hasNext()) {
// String key = iterator.next(); // 得到key
// String value = item.getString(key); // 得到key对应的value
// label = new Label(column++, (i + 1), value); //
// 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
// sheet.addCell(label); // 将单元格加到页
// }
// }
// writableWorkbook.write(); // 加入到文件中
// writableWorkbook.close(); // 关闭文件，释放资源
// } catch (Exception e) {
// result.put("result", "failed"); // 将调用该函数的结果返回
// result.put("reason", e.getMessage()); // 将调用该函数失败的原因返回
// return result;
// }
//
// result.put("result", "successed");
// return result;
// }
// }