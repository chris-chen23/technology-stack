## Java NIO(New IO No Nlocking IO)

#### IO

面向流 Stream Oriented

阻塞IO Blocking IO

![1587114705607](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1587114705607.png)

#### NIO

面向缓冲（Non Blocking IO）通过缓冲区完成数据传输 

选择器 Selectors

![1587115204720](C:/Users/admin/Desktop/java面试题/assert/1587115204720.png)

![1587115442788](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1587115442788.png)



#### 选择器

选择器（Selector） 是 SelectableChannle 对象的多路复用器，Selector 可
以同时监控多个 SelectableChannel 的 IO 状况，也就是说，利用 Selector
可使一个单独的线程管理多个 Channel。Selector 是非阻塞 IO 的核心。

![1587126898581](C:/Users/admin/Desktop/java面试题/assert/1587126898581.png)

![1587129262862](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\1587129262862.png)

