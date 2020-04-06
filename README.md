## 什么是批处理

在大型企业架构里除了大量的交互式应用外，还存在很多后台批处理任务。
针对交互式应用我们有 SpringMVC、Mybatis、Dubbo 等优秀的框架来支撑；
针对批处理任务也同样需要一个优秀的框架来支撑，让我们很方便的开发出健壮的、易用的批处理应用。



**批处理应用通常有以下特点**：

* 批量执行：大批量数据的高效处理，比如数据导入、导出、业务逻辑计算等。
* 对**有限数据**的处理：这不同于 Storm，Flink 等针对无界的流式处理框架。
* 处理过程**没有交互**：无需人工干预，通常以后台常驻进程，自动运行。



## 什么是 Spring Batch
Java 领域中领先的批处理框架，它是埃森哲（Accenture）和 Spring 合作开发的一套优秀的批处理框架。同时 Spring Batch 还被纳入  JSR-352 标准（Batch Applications for the Java Platform）

**Accenture 在批处理架构上有着丰富的工业级别的经验**，贡献了之前专用的批处理体系框架（这些框架历经数十年研发和使用，为 Spring Batch 提供了大量的参考经验）。

通过 Spring Batch 框架可以构建出轻量级的健壮的并行处理应用，支持事务、并发、流程、监控、纵向和横向扩展提供统一的接口管理和任务管理。



## 典型应用场景

* ETL：Extract - Transform - Load，即数据的抽取、转换、加载。
* Report：报表应用，数据抽取、汇总、归类。
* 数据科学领域：比如预测模型、决策模型。
* **非交互式的应用都可以考虑使用批处理框架**。



## Spring Batch 的特性

* 开箱即用
	
	上手快
	
* 支持分支选择
	
	一个 Job 包含 1-N 个独立的 Step，作业流可以根据状态的不同，在各个 Step 进行流转。
	
* 支持多种触发规则
	
	按日期、日历、周期触发。
	
* 支持完整的批处理事物
	支持基于块的处理（Chunk-based）
	
* 声明式的 IO
	提供了非常易用的数据输入、输出支持、是开发者可以专注于业务。
	
* 异常处理
	提供了错误处理的机制，容错机制、重试机制、跳过机制。提供完整的日志查询能力。
	
* 支持并行处理
	
* 可扩展机制
	提供良好的分布式部署能力，以及与第三方框架（比如：Hadoop）的集成能力。

* 建立在 Spring Framework 框架基础之上
	与现有的开发模式保持一致，支持 Ioc、Testing、Spring Boot。



## 批处理关键领域模型及架构

典型的一个作业分为三部分：作业读、作业处理、作业写，也是典型的三步式架构。整个批处理框架基本上围绕Read、Process、Writer来处理。除此之外，框架提供了作业调度器、作业仓库（用以存放 Job 的元数据信息，支持内存、DB两种模式）。

完整的领域概念模型参加下图：

![](https://spring.io/images/diagram-batch-5001274a87227c34b690542c45ca0c9d.svg)



Job Launcher（作业调度器）是 Spring Batch 框架基础设施层提供的运行 Job 的能力。通过给定的 Job 名称和 Job Parameters，可以通过 Job Launcher 执行 Job。

通过 Job Launcher 可以在 Java 程序中调用批处理任务，也可以在通过命令行或者其它框架（如定时调度框架Quartz）中调用批处理任务。

Job Repository 来存储 Job 执行期的元数据（这里的元数据是指 Job Instance、Job Execution、Job Parameters、Step Execution、Execution Context等数据），并提供两种默认实现。

一种是存放在内存中；另一种将元数据存放在数据库中。通过将元数据存放在数据库中，可以随时监控批处理 Job 的执行状态。Job 执行结果是成功还是失败，并且使得在 Job 失败的情况下重新启动 Job 成为可能。Step 表示作业中的一个完整步骤，一个 Job 可以有一个或者多个 Step 组成。



批处理框架运行期的模型也非常简单：

![](http://ww1.sinaimg.cn/large/65e4f1e6jw1f9w4byglo0j20hs08l0t4.jpg)



Job Instance（作业实例）是一个运行期的概念，Job 每执行一次都会涉及到一个 Job Instance。

Job Instance 来源可能有两种：一种是根据设置的 Job Parameters 从 Job Repository（作业仓库）中获取一个；如果根据 Job Parameters 从 Job Repository 没有获取 Job Instance，则新创建一个新的 Job Instance。

Job Execution 表示 Job 执行的句柄，一次 Job 的执行可能成功也可能失败。只有 Job 执行成功后，对应的 Job Instance 才会被完成。因此在 Job 执行失败的情况下，会有一个 Job Instance 对应多个 Job Execution 的场景发生。总结下批处理的典型概念模型，其设计非常精简的十个概念，完整支撑了整个框架。