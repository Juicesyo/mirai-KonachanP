@file:Suppress("unused")

package Juicesyo.github.KonachanP

import net.mamoe.mirai.console.data.ReadOnlyPluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.content

/*
// 定义主类方法 1, 显式提供信息

object MyPluginMain2: KotlinPlugin(
    JvmPluginDescription(
        "org.example.my-plugin",
        "1.0"
    )
)
*/

// 定义主类方法 2, 使用 `JvmPluginDescription.loadFromResource()` 从 resources/plugin.yml 加载

object MyPluginMain : KotlinPlugin(
    @OptIn(ConsoleExperimentalApi::class)
    JvmPluginDescription.loadFromResource()
) {
    /*
    val PERMISSION_EXECUTE_1 by lazy {
        PermissionService.INSTANCE.register(permissionId("KP"), "KonachanP权限")
    }
*/
    override fun onEnable() {
       this.globalEventChannel().subscribeAlways<GroupMessageEvent> {
           if (MySetting.status == "是") {
               if(message.content.startsWith(MySetting.prefix)){
               //if (sender.toString() == "NormalMember(315294716)" && message.content.startsWith(MySetting.prefix)) {
                   var page=message.content.replace("KP","")
                   var Image_Path = Main.main(page).split("\n")
                   for (path in Image_Path){
                       this.group.sendMessage(Image(path))
                   }
                   //this.group.sendMessage(Image("${Main.main(page,sender)}"))
                   //File("").sendAsImageTo(subject)
                   //this.group.sendMessage()
                   //this.group.sendMessage(Image("${Main.main(message.content.replace("KP",""))}"))
               }
           }
       }

        MySetting.reload() // 从数据库自动读取配置实例
        //MyPluginData.reload()

        //logger.info { "Hi: ${MySetting.name}" } // 输出一条日志.
        //logger.info("Hi: ${MySetting.name}") // 输出一条日志. 与上面一条相同, 但更推荐上面一条.
        //logger.verbose("Hi: ${MySetting.name}") // 多种日志级别可选
        //logger.info{"${Main.main("1")}"}

        // 请不要使用 println, System.out.println 等标准输出方式. 请总是使用 logger.

        //MySimpleCommand.register() // 注册指令

        //PERMISSION_EXECUTE_1 // 初始化, 注册权限
    }
/*
    override fun onDisable() {
        MySimpleCommand.unregister() // 取消注册指令
    }
*/
}

// 定义插件数据
// 插件
/*
object MyPluginData : AutoSavePluginData("mirai-KonachanP-1.0") { // "name" 是保存的文件名 (不带后缀)
    var list: MutableList<String> by value(mutableListOf("a", "b")) // mutableListOf("a", "b") 是初始值, 可以省略
    var long: Long by value(0L) // 允许 var
    var int by value(0) // 可以使用类型推断, 但更推荐使用 `var long: Long by value(0)` 这种定义方式.


    // 带默认值的非空 map.
    // notnullMap[1] 的返回值总是非 null 的 MutableMap<Int, String>
    var notnullMap
            by value<MutableMap<Int, MutableMap<Int, String>>>().withEmptyDefault()

    // 可将 MutableMap<Long, Long> 映射到 MutableMap<Bot, Long>.
    val botToLongMap: MutableMap<Bot, Long> by value<MutableMap<Long, Long>>().mapKeys(Bot::getInstance, Bot::id)
}
*/
// 定义一个配置. 所有属性都会被追踪修改, 并自动保存.
// 配置是插件与用户交互的接口, 但不能用来保存插件的数据.
object MySetting : ReadOnlyPluginConfig("MySetting") { // "MySetting" 是保存的文件名 (不带后缀)
    //val name by value("config")

    @ValueDescription("是否启用插件") // 注释, 将会保存在 MySetting.yml 文件中.
    val status by value("是")
    @ValueDescription("命令前缀")
    val prefix by value("KP")
    @ValueDescription("图片保存路径")
    val path by value("C:/Users/Juice/Desktop/images/")
    //val count by value(0)

    //val nested by value<MyNestedData>() // 嵌套类型是支持的
}
/*
@Serializable
data class MyNestedData(
    val list: List<String> = listOf()
)
 */