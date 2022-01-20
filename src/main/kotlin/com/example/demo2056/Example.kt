package com.example.demo2056

import org.hibernate.annotations.Columns
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import org.jadira.usertype.moneyandcurrency.moneta.PersistentCurrencyUnit
import org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmountAndCurrency
import org.javamoney.moneta.Money
import java.io.Serializable
import javax.money.CurrencyUnit
import javax.persistence.*

@Entity
@TypeDefs(
    TypeDef(name = "Money", typeClass = PersistentMoneyAmountAndCurrency::class, defaultForType = Money::class),
    TypeDef(name = "CurrencyUnit", typeClass = PersistentCurrencyUnit::class, defaultForType = CurrencyUnit::class),
)
class Example(price: Money?) : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Columns(
        columns = [
            Column(name = "price_currency", nullable = false),
            Column(name = "price_amount", nullable = false)
        ]
    )
    var price: Money? = price

    override fun toString(): String =
        "Example(price=$price)"

}
