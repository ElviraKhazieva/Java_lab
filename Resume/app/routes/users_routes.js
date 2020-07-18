module.exports = function (app) {
    app.get('/profile', (request, response) => {
        var result = {
            "first_name": "Эльвира",
            "last_name": "Хазиева",
            "age" : "18",
            "birthday": "Дата рождения: 09.12.2001",
            "profession": "Java-разработчик",
            "skills" : [
                "Отличные знания Java",
                "Знание алгоритмов и структур данных, оценка сложности",
                "СУБД MS PostgreSQL",
                "Знания JavaScript, HTML, CSS",
                "знание Git"
            ],
            "personal_qualities": [
                "Коммуникабельность",
                "Целеустремленность",
                "Исполнительность",
                "Ответственность",
                "Умение работать в команде"
            ],
            "work_experience": [
                "ООО АйТиБрик, Казань – junior Java-разработчик",
                "июнь 2018 – июль 2020"
            ],
            "duties": [
                "Разработка веб-приложений",
                "Написание и сопровождение кода",
                "Участие в оценке и планировании задач"
            ],
            "education": [
                "Вуз: КФУ (бывш. КГУ им. Ульянова-Ленина)",

                "Факультет: Высшая школа информационных технологий и интеллектуальных систем",

                "Специальность: Программная инженерия"
            ],
            "contacts": [
                "Phone: 89120210392",
 
                "Email: Khaz.elvira@yandex.ru",

                "Telegram: @khelvira",

                "Vk: https://vk.com/elvira_khazieva"
            ]
        };
        response.send(JSON.stringify(result));
    });
};
