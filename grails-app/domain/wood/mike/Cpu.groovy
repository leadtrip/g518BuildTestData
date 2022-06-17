package wood.mike

import groovy.transform.ToString

@ToString(includePackage = false, allProperties = false)
class Cpu {

    String brand
    String model
    String socket
    String architecture
    Integer cores
    Integer threads
    Double clockSpeed

    static constraints = {
        brand nullable: false, unique: 'model'
        model nullable: false
        socket nullable: false
        architecture nullable: false
        cores nullable: false
        threads nullable: false
        clockSpeed nullable: false
    }
}
