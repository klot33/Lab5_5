import kotlin.math.sqrt

// Класс для точки
data class Point(val x: Double, val y: Double)

// Класс для треугольника
class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    // Функция для вычисления длин сторон треугольника
    private fun sideLength(p1: Point, p2: Point): Double {
        return sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y))
    }

    // Вычисление координат инцентра (центра вписанной окружности)
    fun incenter(): Point {
        val a = sideLength(p2, p3)
        val b = sideLength(p1, p3)
        val c = sideLength(p1, p2)

        val x = (a * p1.x + b * p2.x + c * p3.x) / (a + b + c)
        val y = (a * p1.y + b * p2.y + c * p3.y) / (a + b + c)

        return Point(x, y)
    }

    // Вычисление радиуса вписанной окружности
    fun inradius(): Double {
        val a = sideLength(p2, p3)
        val b = sideLength(p1, p3)
        val c = sideLength(p1, p2)

        // Полупериметр
        val p = (a + b + c) / 2

        // Площадь треугольника по формуле Герона
        val area = sqrt(p * (p - a) * (p - b) * (p - c))

        // Радиус вписанной окружности
        return area / p
    }
}

// Функция для безопасного ввода координат с обработкой ошибок
fun readPointCoordinate(prompt: String): Double {
    while (true) {
        try {
            print(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Ошибка: введите корректное число.")
        }
    }
}

fun main() {
    // Ввод координат точек треугольника
    println("Введите координаты вершин треугольника:")

    val x1 = readPointCoordinate("Точка A (x1): ")
    val y1 = readPointCoordinate("Точка A (y1): ")
    val x2 = readPointCoordinate("Точка B (x2): ")
    val y2 = readPointCoordinate("Точка B (y2): ")
    val x3 = readPointCoordinate("Точка C (x3): ")
    val y3 = readPointCoordinate("Точка C (y3): ")

    // объект треугольника
    val triangle = Triangle(Point(x1, y1), Point(x2, y2), Point(x3, y3))

    // центр вписанной окружности
    val incenter = triangle.incenter()

    // радиус вписанной окружности
    val inradius = triangle.inradius()

    // Вывод результатов
    println("Центр вписанной окружности: (${incenter.x}, ${incenter.y})")
    println("Радиус вписанной окружности: $inradius")
}