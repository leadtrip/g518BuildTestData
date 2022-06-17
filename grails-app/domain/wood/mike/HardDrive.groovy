package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false, allProperties = false)
class HardDrive {

    String brand
    String model
    Integer capacity
    String formFactor
    String iface

    static constraints = {
        brand nullable: false, unique: 'model'
        model nullable: false
        capacity nullable: false
        formFactor nullable: false
        iface nullable: false
    }
}
