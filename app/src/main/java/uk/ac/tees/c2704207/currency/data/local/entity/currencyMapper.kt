package uk.ac.tees.c2704207.currency.data.local.entity

import uk.ac.tees.c2704207.currency.domain.model.currencyRate

fun currencyRateEntity.toCurrencyRate(): currencyRate{
    return currencyRate(

        code = code,
        name = name,
        rate = rate

    )


}
fun currencyRate.toCurrencyRateEntity(): currencyRateEntity {
    return currencyRateEntity(

        code = code,
        name = name,
        rate = rate

    )
}