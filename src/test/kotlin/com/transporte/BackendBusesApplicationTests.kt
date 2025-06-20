package com.transporte

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
  properties = [
    "spring.jpa.defer-datasource-initialization=true",
    "spring.sql.init.mode=never"
  ]
)
class BackendBusesApplicationTests {

    @Test
    fun contextLoads() {
        // ahora sólo carga el ApplicationContext, sin ejecutar data.sql
    }

}
