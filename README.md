# Reboot School Java Developer Final Project

---

[![build](https://img.shields.io/github/workflow/status/AleksMikhailenko/reboot-final-project/Java%20CI%20with%20Maven/develop)](https://github.com/AleksMikhailenko/reboot-final-project/actions?query=branch%3Adevelop++workflow%3A%22Java+CI+with+Maven%22++)
[![codecov](https://codecov.io/gh/AleksMikhailenko/reboot-final-project/branch/develop/graph/badge.svg)](https://codecov.io/gh/AleksMikhailenko/reboot-final-project)

[comment]: <> ([![Reliability Rating]&#40;https://sonarcloud.io/api/project_badges/measure?project=AleksMikhailenko_home-work&metric=reliability_rating&#41;]&#40;https://sonarcloud.io/dashboard?id=AleksMikhailenko_home-work&#41;)

[comment]: <> ([![Security Rating]&#40;https://sonarcloud.io/api/project_badges/measure?project=AleksMikhailenko_home-work&metric=security_rating&#41;]&#40;https://sonarcloud.io/dashboard?id=AleksMikhailenko_home-work&#41;)

[comment]: <> ([![Maintainability Rating]&#40;https://sonarcloud.io/api/project_badges/measure?project=AleksMikhailenko_home-work&metric=sqale_rating&#41;]&#40;https://sonarcloud.io/dashboard?id=AleksMikhailenko_home-work&#41;)

[comment]: <> ([![Duplicated Lines &#40;%&#41;]&#40;https://sonarcloud.io/api/project_badges/measure?project=AleksMikhailenko_home-work&metric=duplicated_lines_density&#41;]&#40;https://sonarcloud.io/dashboard?id=AleksMikhailenko_home-work&#41;)

---

## Модель получения клиентом баланса на банкомате

Построить модель получения клиентом баланса по карте на банкомате.
Классы доменной модели ничего не должны знать о техническом окружении.
Подробности технического окружения должны быть скрыты за слоями абстракции.

В качестве системы сборки должен использоваться Maven.
В финальной версии приложение должно представлять 2 Spring Boot приложения.
Одно будет выполнять роль клиента/банкомата, а другое роль сервера.
Приложение с ролью сервер должно хранить свои данные в базе данных H2.
Для взаимодействия приложения использовать REST. Модули должны быть покрыты JUnit тестами.

Проложение с ролью сервер должно требовать аутентификации по логину и паролю с использованием Spring Security.

В качестве задания на 5+ приложения должны уметь переключаться на взаимодействие через Apache Kafka.