package wood.mike

import grails.buildtestdata.BuildDataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MotherboardServiceSpec extends Specification implements ServiceUnitTest<MotherboardService>, BuildDataTest{

    void setupSpec() {
        mockDomains Motherboard, Cpu, Ram, HardDrive, GraphicsCard
    }

    def setup() {
        buildComponents()
    }

    void "test all"() {
        when:
            def allMobos = service.all()
        then:
            allMobos.size() == 2
            allMobos.each {println it}
    }

    void "test all with one test data build"() {
        given:
            Motherboard.build()
        when:
            def allMobos = service.all()
        then:
            allMobos.size() == 3
            allMobos.each {println it}
    }

    void "test all with two test data build"() {
        given:
            Motherboard.build()   // works fine first time but would fail if did it again due to unique constraints on brand and model on all components

            def cpu = Cpu.build(brand: 'Intel', model: 'IN123')     // so we build the individual components
            def hd = HardDrive.build(brand: 'Seagate', model: 'SG123')
            def gc = GraphicsCard.build(brand: 'ASROCK', model: 'AS123')
            def ra = Ram.build(brand: 'Corsair', model: 'CO123')
            Motherboard.build( brand: 'MSI', model: 'jjajdkfajdsiaj', cpu: cpu, hardDrives: [hd], graphicsCard: gc, memory: [ra]) // and add to 2nd mobo
        when:
            def allMobos = service.all()
        then:
            allMobos.size() == 4
            allMobos.each {println it}
    }

    void "test findOrBuild"() {
        when:
            findOrBuild(Motherboard, [brand: 'MSI', model: 'X570-A PRO'])   // added in buildComponents called by setup below
        then:
            service.all().size() == 2
    }

    void "test doWithTestDataConfig"() {
        when:
            def mobo = Motherboard.build()
        then: 'ensure the defaults set in /resources/TestDataConfig.groovy take effect'
            mobo.brand == 'Gigabyte'
            mobo.cpu.brand == 'Intel'
    }

    def buildComponents() {
        def amdCpu = new Cpu(brand: 'AMD', model: 'Ryzen 5', socket: 'AM4', architecture: 'Zen 2', cores: 6, threads: 12, clockSpeed: 3.6)
        def intelCpu = new Cpu(brand: 'Intel', model: 'Core I7', socket: '1700', architecture: 'Alder lake', cores: 12, threads: 20, clockSpeed: 2.1)

        def m2ssd = new HardDrive( brand: 'Western digital', model: 'WDS240G2G0C', capacity: 240, formFactor: 'M2', iface: 'PCIe(x4)' )
        def mechHdd = new HardDrive( brand: 'Toshiba', model: 'HDWD110UZSVA', capacity: 1000, formFactor: '3.5', iface: 'SATA 3.0 (6Gb/s)' )

        def amdGraphics = new GraphicsCard(brand: 'MSI', model: 'TUF-GTX1660TI-6G-EVO-GAMING', chipset: 'Radeon RX 6800 XT', memory: 16, memoryType: 'GDDR6', memoryClock: 16000 )
        def nvidiaGraphics = new GraphicsCard(brand: 'ASUS', model: 'TUF Gaming EVO', chipset: 'GeForce GTX 1660 Ti', memory: 6, memoryType: 'GDDR6', memoryClock: 12000 )

        def ram = new Ram(brand: 'Corsair', model: 'CMK32GX4M2Z3600C18', type: 'DDR4', channels: 2, capacity: 32 )

        def amdMobo = new Motherboard( brand: 'MSI', model: 'X570-A PRO', cpu: amdCpu, graphicsCard: amdGraphics )
        amdMobo.addToMemory(ram)
        amdMobo.addToHardDrives(m2ssd)
        amdMobo.save()

        def intelMobo = new Motherboard( brand: 'ASUS', model: 'PRIME Z690-P D4', cpu: intelCpu, graphicsCard: nvidiaGraphics )
        intelMobo.addToMemory( ram )
        intelMobo.addToHardDrives(mechHdd)
        intelMobo.save()
    }
}
