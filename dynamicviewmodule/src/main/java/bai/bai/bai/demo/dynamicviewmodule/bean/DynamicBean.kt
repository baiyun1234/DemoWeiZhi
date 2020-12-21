package bai.bai.bai.demo.dynamicviewmodule.bean

class DynamicBean {

    var label: String? = null//Label
    var fieldName: String? = null//字段名称
    var fieldInput: String? = null//获取的字段值
    var fieldType: String? = null//字段类型
    var fieldValue: String? = null//字段值
    var isRequired: Boolean = false//是否必填

    var description: String? = null//补充描述
    var inputType: String? = null//输入类型
    var inputLength: String? = null//输入长度

    override fun toString(): String {
        return "DynamicBean(label=$label, fieldName=$fieldName, fieldInput=$fieldInput, fieldType=$fieldType, fieldValue=$fieldValue, isRequired=$isRequired, description=$description, inputType=$inputType, inputLength=$inputLength)"
    }

}