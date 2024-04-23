# Linhas de um cliente   

Microserviço desenvolvido em Spring Boot para retornar as linhas de um cliente.

## Depencências

* IDE recomendada **Intellij**
* Java 17

#### Configurações

Para clonar o projeto usando Git, execute:
```
git clone https://github.com/Doni-zete/customer-line.git
```

#### Maven
Na pasta do projeto, execute:
```
mvn clean install
```

## Rotas

As rotas são definidas na camada de Controller definida pela anotação, e processadas na camada de Service definida pela anotação @Service.

### Controller
```
@RestController
public class ExampleController {

    @Autowired
    private ExampleServiceImpl exampleService;
...
```

Nesta camada também são definidas as rotas a através da anotação @GetMapping, segue exemplo:
```
    @GetMapping("/linha")
      public ResponseEntity<List<LinhaDto>> getLinesCpfOrCnpj(
           return ResponseEntity.ok(linhasDtoList));

  }

```
Lembrando que foi usado apenas a anotação @GetMapping refere-se a HTTP GET.

### Service
Foi implementado uma Interface, porém a anotação vai somente
na implementação e nao na Interface.

Segue exemplo da Interface:
```
public interface ExampleService {

    ExampleDto findteExample(example example);
}
...
```

Segue exemplo do Service:
```
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository repository;

    @Override
    public ExampleDto findExample(example example) {
        Example example = new Example();
        example.setExemple(exemple);
        return exempleExemple.ExampleDto(example);
    }
```

### Acessando a Rota
Para acessar uma rota

Logo, locamente a rota do exemplo ficaria:

`http://localhost:8081/linha?customer=digite-cpfcnpj`


`http://localhost:8081/linha?customer=digite-cpfcnpj&status=ativo`

## Testes
### Unitário
Testes unitários são implementados usando JUnit 5 (jupiter) e o Mockito.

Segue exemplo de implementação:
```
@ExtendWith(MockitoExtension.class)
public class ExampleControllerTests {
    @InjectMocks
    private ExampleController exampleController;

    @Mock
    private ExampleServiceImpl exampleService;

    @Test
    public void shouldDeleteExample() {
        Long exampleId = 1L;

        ResponseEntity<?> response = exampleController.deleteExample(exampleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(exampleService, times(1)).deleteExample(exampleId);
    }
    }
```
Os testes unitários podem ser executados diretamente pela IDE ou pelo Maven através do seguinte comando:
``mvn test``


### Integração
Testes de componente são implementados usando restTemplate e JUnit.


```
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerLineControllerIntegrationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetLinesCpfOrCnpj() {


    ResponseEntity<List<LinhaDto>> response = restTemplate.exchange(
      "http://localhost:8081/linha?customer=digite-cpfcnpj&status=ativo",
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<LinhaDto>>() {
      }
    );

    assertEquals(HttpStatus.OK, response.getStatusCode());

    List<LinhaDto> linhasDtoList = response.getBody();
    assertNotNull(linhasDtoList);
  }
}

```
Os testes de integração podem ser executados diretamente pela IDE ou pelo Maven através do seguinte comando:
Lembrando que o servidor com a porta http://localhost:8081/ tem que esta rodando para fazer o teste
``mvn verify``

### Mutação
Testes de Mutação servem para avaliar a qualidade dos testes unitários, logo implementações serão melhorias
feitas nos próprios testes unitários.

O projeto utiliza a lib **pitest** para gerar este report.

Para gerar o report, execute:

``mvn test-compile org.pitest:pitest-maven:mutationCoverage``


Segue exemplo de um report com % de cobertura:



