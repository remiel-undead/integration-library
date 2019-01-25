package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.Quantity
import ru.evotor.framework.core.*
import java.math.BigDecimal

internal object QuantityMapper {
    fun read(
            cursor: Cursor,
            columnUnscaledValue: String,
            columnScale: String,
            columnUnitOfMeasurementVariationId: String,
            columnUnitOfMeasurementName: String,
            columnUnitOfMeasurementType: String
    ): Quantity? {
        return Quantity(
                value = cursor.safeGetBigDecimal(columnUnscaledValue)
                        ?.divide(BigDecimal(1000))
                        ?.setScale(cursor.safeGetInt(columnScale) ?: 0)
                        ?: return null,
                unitOfMeasurement = UnitOfMeasurementMapper.read(
                        cursor,
                        columnUnitOfMeasurementVariationId,
                        columnUnitOfMeasurementName,
                        columnUnitOfMeasurementType
                ) ?: return null
        )
    }
}