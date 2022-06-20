testDataConfig {
    sampleData {
        'wood.mike.Motherboard' {
            brand = "Gigabyte"
        }
        'wood.mike.Cpu' {
            brand = { -> 'Intel' }
            def cpuModelIdx = 1
            model = { "IN${cpuModelIdx++}" }
        }
    }
}