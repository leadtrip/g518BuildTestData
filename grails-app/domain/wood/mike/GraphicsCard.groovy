package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false, allProperties = false)
class GraphicsCard {

    String brand
    String model
    String chipset
    Integer memory
    String memoryType
    Integer memoryClock

    static constraints = {
        brand nullable: false, unique: 'model'
        model nullable: false
        chipset nullable: false
        memory nullable: false
        memoryType nullable: false
        memoryClock nullable: false
    }
}
