package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false, allProperties = false)
class Ram {

    String brand
    String model
    String type
    Integer channels
    Integer capacity

    static constraints = {
        brand nullable: false, unique: 'model'
        model nullable: false
        type nullable: false
        channels nullable: false
        capacity nullable: false
    }
}
