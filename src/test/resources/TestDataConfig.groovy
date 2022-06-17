testDataConfig {
    sampleData {
        'wood.mike.Motherboard' {
            brand = "Gigabyte"
        }
        'wood.mike.Cpu' {
            brand = { -> 'Intel' }
        }
    }
}