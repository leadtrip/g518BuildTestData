package wood.mike

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.TestDataConfigurationHolder
import spock.lang.Specification

class CpuSpec extends Specification implements BuildDataTest{

    void setupSpec() {
        mockDomains Cpu
    }

    void cleanup() {
        TestDataConfigurationHolder.reset()
    }

    void "test unique model from build test data"() {
        when:
            (1..10).collect {Cpu.build()}
        then:
            def allCpus = Cpu.all
            def allBrands = allCpus.brand.unique()
            allBrands.size() == 1
            allBrands[0] == 'Intel'

            def allModels = allCpus.model.unique()
            allModels.size() == 10
            allModels.containsAll( ['IN1', 'IN2', 'IN3', 'IN4', 'IN5', 'IN6', 'IN7', 'IN8', 'IN9', 'IN10'] )
    }
}
