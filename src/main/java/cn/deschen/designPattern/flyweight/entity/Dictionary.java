package cn.deschen.designPattern.flyweight.entity;

/**
 * @Author hanbin_chen
 * @Description 数据字典
 *              特点：整个系统共享，使用频繁
 * @Version V1.0.0
 */
public class Dictionary {

    /**
     * 唯一标识
     */
    private Long id;

    /**
     * 所属组编码
     */
    private String groupCode;

    /**
     * 所属组名
     */
    private String groupName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典值
     */
    private String dictValue;

    /**
     * 描述
     */
    private String description;

    public Dictionary() {
    }

    public Dictionary(Long id, String groupCode, String groupName, String dictCode, String dictValue, String description) {
        this.id = id;
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.dictCode = dictCode;
        this.dictValue = dictValue;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", groupCode='" + groupCode + '\'' +
                ", groupName='" + groupName + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictValue='" + dictValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}