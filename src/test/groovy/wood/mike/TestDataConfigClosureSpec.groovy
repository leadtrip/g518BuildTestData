package wood.mike

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.TestDataConfigurationHolder
import spock.lang.Specification

class TestDataConfigClosureSpec extends Specification implements BuildDataTest{

    void setupSpec() {
        mockDomains Motherboard
    }

    Closure doWithTestDataConfig() {{ ->
        testDataConfig {
            sampleData {
                'wood.mike.Motherboard' {
                    brand = { -> 'Jupiter' }
                }
                'wood.mike.Cpu' {
                    brand = { -> 'Saturn' }
                }
            }
        }
    }}

    void cleanup() {
        TestDataConfigurationHolder.reset()
    }

    void "test config closure" () {
        when:
            def mobo = Motherboard.build()
        then:
            mobo.brand == 'Jupiter'
            mobo.cpu.brand == 'Saturn'
    }
}
