package wood.mike

import grails.gorm.transactions.Transactional

@Transactional
class MotherboardService {

    def all() {
        Motherboard.all
    }
}
