package nyxdev.hackatren.taralrt1.global.model.obj.eta
class Fare{
    
    public var currency: String = ""

    public var text: String = ""

    public var value: Int = 0

    constructor() 

    constructor(currency: String, text: String, value: Int){
        this.currency = currency
        this.text = text
        this.value = value
    }

}
