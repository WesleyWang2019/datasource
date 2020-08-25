数据结构和算法
算法的优劣 时间测算（计算算法时间差、幅度不够循环来凑）、空间测算

big O
O(1)时间不变
O(n)随着问题规模的扩大，时间也随着扩大

验证算法
1、肉眼观察
2、产生足够多的样本
3、用确定正确的算法计算样本结果
4、对比被验证算法的结果

简单排序算法总结
冒泡 基本不用，太慢   每排一次，最后一个值是有序的
int i = 0; i < arr.length; i++  int j = 0; j < arr.length - i - 1; j++

选择 基本不用，不稳   选择最值排在第一个
int i = 0; i < arr.length - 1; i++   int j = i + 1; j < arr.length; j++

插入 样本小且基本有序时效率很高 i范围内进行排序
int i = 1; i < arr.length; i++ int j = i; j > 0; j--

希尔排序 间隔大时移动次数少，间隔小时移动次数少 间隔为gap的位置值是有序的
        获取间隔
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        //
        for(int gap = h;gap > 0 ; gap = (gap-1)/3){
        //从间隔位置开始
            for(int i = gap ; i < arr.length -1 ; i ++){
                for(int j = i ; j > gap - 1 ; j =-gap){
                    if(arr[j] > arr[j-gap]) swap();
            
                }
            }
        }
归并排序
TIM SORT JAVA对象排序专用（改进的归并排序） 对于一个二分后有序数组排序 
对象排序 对象排序一般要求稳定      
        
快速排序（单轴快排、双轴快排）

计数排序 适用于量大但是取值范围小
        