# IITDS Spring Web API

A Spring Boot web API that calculates individual income tax scenarios.

## Requirements

- Java 21
- Maven (or use the included Maven wrapper)
- Local Maven artifact `com.yahaha:iit:1.0-SNAPSHOT` available in your Maven repository

## Build

```bash
./mvnw clean package
```

The fat jar will be created at:

```
target/iitds-0.0.1-SNAPSHOT.jar
```

## Run

### Option 1: Maven

```bash
./mvnw spring-boot:run
```

### Option 2: Jar

```bash
java -jar target/iitds-0.0.1-SNAPSHOT.jar
```

The service starts on `http://localhost:8080` by default.

## API Usage

### POST /api/v1/simulate

Content-Type: `application/json`

Example request:

```bash
curl -X POST "http://localhost:8080/api/v1/simulate" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{"annualWageIncome":216000,"annualOneTimeBonus":300000}'
```

Example response:

```json
{
  "results": {
    "ONE_TIME_TAXATION": {
      "items": [
        {
          "taxBaseAmount": "¥156,000.00",
          "taxAmount": "¥14,280.00",
          "taxRate": 0.2,
          "traceLog": {
            "subLogs": [
              {
                "header": "计算全年应纳税综合所得额",
                "body": [
                  {
                    "label": "年度工资收入",
                    "amount": "¥216,000.00"
                  },
                  {
                    "label": "扣除：减除费用",
                    "amount": "¥-60,000.00"
                  }
                ],
                "footer": "全年应纳税综合所得额: ¥156,000.00"
              },
              {
                "header": "通过查询税率表，应使用第3档税率，税率为20%",
                "body": [
                  {
                    "label": "税基 x 税率 =",
                    "amount": "¥31,200.00"
                  },
                  {
                    "label": "扣除：速算扣除数",
                    "amount": "¥-16,920.00"
                  },
                  {
                    "label": "最终税额",
                    "amount": "¥14,280.00"
                  }
                ],
                "footer": "应纳税额: ¥14,280.00"
              }
            ]
          }
        },
        {
          "taxBaseAmount": "¥300,000.00",
          "taxAmount": "¥58,590.00",
          "taxRate": 0.2,
          "traceLog": {
            "subLogs": [
              {
                "header": "计算全年一次性奖金应纳税部分",
                "footer": "全年一次性奖金应纳税部分: ¥300,000.00"
              },
              {
                "header": "通过查询税率表，应使用第3档税率，税率为20%",
                "body": [
                  {
                    "label": "税基 x 税率 =",
                    "amount": "¥60,000.00"
                  },
                  {
                    "label": "扣除：速算扣除数",
                    "amount": "¥-1,410.00"
                  },
                  {
                    "label": "最终税额",
                    "amount": "¥58,590.00"
                  }
                ],
                "footer": "应纳税额: ¥58,590.00"
              }
            ]
          }
        }
      ],
      "totalTaxAmount": "¥72,870.00"
    },
    "INTEGRATED_TAXATION": {
      "items": [
        {
          "taxBaseAmount": "¥456,000.00",
          "taxAmount": "¥83,880.00",
          "taxRate": 0.3,
          "traceLog": {
            "subLogs": [
              {
                "header": "计算全年应纳税综合所得额",
                "body": [
                  {
                    "label": "年度工资收入",
                    "amount": "¥216,000.00"
                  },
                  {
                    "label": "全年一次性奖金",
                    "amount": "¥300,000.00"
                  },
                  {
                    "label": "扣除：减除费用",
                    "amount": "¥-60,000.00"
                  }
                ],
                "footer": "全年应纳税综合所得额: ¥456,000.00"
              },
              {
                "header": "通过查询税率表，应使用第5档税率，税率为30%",
                "body": [
                  {
                    "label": "税基 x 税率 =",
                    "amount": "¥136,800.00"
                  },
                  {
                    "label": "扣除：速算扣除数",
                    "amount": "¥-52,920.00"
                  },
                  {
                    "label": "最终税额",
                    "amount": "¥83,880.00"
                  }
                ],
                "footer": "应纳税额: ¥83,880.00"
              }
            ]
          }
        }
      ],
      "totalTaxAmount": "¥83,880.00"
    }
  }
}
```

## Tests

```bash
./mvnw test
```
