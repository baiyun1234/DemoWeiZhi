package bai.bai.bai.demo.dynamicviewmodule.bean

class DynamicBean {

    var label: String? = null//Label
    var fieldName: String? = null//字段名称
    var fieldInput: String? = null//获取的字段值
    var fieldType: String? = null//字段类型
    var fieldValue: String? = null//字段值
    var isRequired: Boolean = false//是否必填


    override fun toString(): String {
        return "DynamicBean(label=$label, fieldName=$fieldName, fieldInput=$fieldInput, fieldType=$fieldType, fieldValue=$fieldValue, isRequired=$isRequired)"
    }

}