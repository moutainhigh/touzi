package com.river.ms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器演�?
 * </p>
 */
public class MpGenerator {

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("E://");
		gc.setFileOverride(true);
		gc.setActiveRecord(false);// 不需要ActiveRecord特�?�的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
//		gc.setKotlin(true); //是否生成 kotlin 代码
		gc.setAuthor("zyb");

		// 自定义文件命名，注意 %s 会自动填充表实体属�?�！
		gc.setMapperName("%sDao");
		gc.setXmlName("%sDao");
		gc.setServiceName("MP%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 数据源配�?
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("root");
		dsc.setUrl(
				"jdbc:mysql://localhost/test?serverTimezone=GMT&useUnicode=true&characterEncoding=utf8");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		List<String> business = new ArrayList<String>();

		/*strategy.setInclude("project_approval", "project_bbs", "project_business_history", "project_completion",
				"project_condition", "project_condition_feedback", "project_council", "project_council_member",
				"project_data", "project_decision", "project_document", "project_expert", "project_feasibility",
				"project_financial_balance", "project_financial_cash", "project_financial_income",
				"project_financial_index", "project_history", "project_indicator_data", "project_keypoint",
				"project_list_ext", "project_member", "project_milestone", "project_milestone_cost",
				"project_milestone_history", "project_modification", "project_operation_data", "project_opinion",
				"project_opinion_condition", "project_setup"); // �?要生成的�?
*/		
		strategy.setInclude("department");
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父�?
		// strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定�? mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定�? service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定�? service 实现类父�?
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定�? controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		// 【实体�?�是否生成字段常量（默认 false�?
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体�?�是否为构建者模型（默认 false�?
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 包配�?
		PackageConfig pc = new PackageConfig();
		pc.setParent("workSpace\\weshop\\src\\main\\java\\com\\rayootech\\weshop");
		// pc.setModuleName("test");
		mpg.setPackageInfo(pc);

		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改�?
		// 放置自己项目�? src/main/resources/templates 目录�?, 默认名称�?下可以不配置，也可以自定义模板名�?
		// TemplateConfig tc = new TemplateConfig();
		// tc.setController("...");
		// tc.setEntity("...");
		// tc.setMapper("...");
		// tc.setXml("...");
		// tc.setService("...");
		// tc.setServiceImpl("...");
		// 如上任何�?个模块如果设�? �? OR Null 将不生成该模块�??
		// mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();
	}

}
