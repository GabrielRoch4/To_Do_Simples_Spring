/*const url = "http://localhost:8080/task/user/2"

function hideLoader() {
    document.getElementById("loading").style.display = "none"
}

function show(tasks) {
    let tab = `
        <thead>
            <th scope="col">
                ID
            </th>

            <th scope="col">
                Description
            </th>

            <th scope="col">
                Username
            </th>
        </thead>
    `

    for(let task of tasks) {
        tab += `
            <tr>
                <td scope="raw">
                    ${task.id}
                </td>

                <td scope="raw">
                    ${task.description}
                </td>

                <td scope="raw">
                    ${task.user.username}
                </td>
            </tr>
        `
    }

    document.getElementById("tasks").innerHTML = tab
}

async function getAPI(url) {
    const response = await fetch(url, {method: "GET"})

    var data = await response.json()
    console.log(data)

    if(response) {
        hideLoader()
        show(data)
    }
}

getAPI(url);*/