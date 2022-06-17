package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false, allProperties = false)
class Motherboard {

    String brand
    String model
    Cpu cpu
    GraphicsCard graphicsCard
    static hasMany = [memory: Ram, hardDrives: HardDrive]

    static constraints = {
        brand nullable: false, unique: 'model'
        model nullable: false
        cpu nullable: false
        graphicsCard nullable: false
    }

}
