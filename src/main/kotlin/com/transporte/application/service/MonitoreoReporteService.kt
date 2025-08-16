package com.transporte.application.service

import com.transporte.application.dto.TopErroresRow
import com.transporte.infrastructure.repository.MonitoreoReporteRepository
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.time.OffsetDateTime

@Service
class MonitoreoReporteService(
    private val repo: MonitoreoReporteRepository
) {
    fun topErroresXlsx(from: OffsetDateTime?, to: OffsetDateTime?, limit: Int = 50): ByteArray {
        val rows = repo.topErrores(from, to, limit).map { r ->
            TopErroresRow(
                actividad       = r[0]?.toString(),
                componente      = r[1]?.toString(),
                total           = (r[2] as Number).toLong(),
                erroresApp      = (r[3] as Number).toLong(),
                invalidosIngesta= (r[4] as Number).toLong(),
                pctError        = (r[5] as Number).toDouble(),
                pctInvalid      = (r[6] as Number).toDouble()
            )
        }

        val wb = XSSFWorkbook()
        val sh = wb.createSheet("TopErrores")
        var i = 0
        val header = listOf("actividad","componente","total","errores_app","invalidos_ingesta","%error","%invalid")
        sh.createRow(i++).also { h -> header.forEachIndexed { c, v -> h.createCell(c).setCellValue(v) } }
        rows.forEach { row ->
            sh.createRow(i++).apply {
                createCell(0).setCellValue(row.actividad ?: "")
                createCell(1).setCellValue(row.componente ?: "")
                createCell(2).setCellValue(row.total.toDouble())
                createCell(3).setCellValue(row.erroresApp.toDouble())
                createCell(4).setCellValue(row.invalidosIngesta.toDouble())
                createCell(5).setCellValue(row.pctError)
                createCell(6).setCellValue(row.pctInvalid)
            }
        }
        (0 until header.size).forEach { sh.autoSizeColumn(it) }
        return ByteArrayOutputStream().use { out -> wb.write(out); wb.close(); out.toByteArray() }
    }
}
