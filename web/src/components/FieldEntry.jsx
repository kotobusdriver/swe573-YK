import {useState} from "react";
import {v4 as uuidv4} from 'uuid';

function FieldEntry({field, onValueChange}) {
    const [value, setValue] = useState(field.name);
    const [uniqueId, setUniqueId] = useState(uuidv4());

    function changeValue(v) {
        setValue(v)
        onValueChange(v)
    }

    function uploadFile(file) {
        const fileName = uniqueId + "-" + file.name;
        const url = `/files/${fileName}`

        const formData = new FormData();
        formData.append("file", file);

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        fetch(url, requestOptions)
            .catch(error => console.log("An error occurred!", error));

        changeValue(url);
    }

    function renderText(field) {
        return (
            <input type="text" className="form-control" id="inputFieldText"
                   value={value}
                   onChange={(e) => changeValue(e.target.value)}/>
        )
    }

    function renderImage(field) {
        return (
            <input type="file" className="form-control" id="inputFieldImage" accept="image/png, image/jpeg" onChange={(e) => uploadFile(e.target.files[0])}/>
        )
    }

    function renderAttachment(field) {
        return (
        <input type="file" className="form-control" id="inputFieldAttachment"onChange={(e) => uploadFile(e.target.files[0])}/>
        )
    }

    function renderDate(field) {
        return (
            <input type="date" className="form-control" id="inputFieldDate"
                   value={value}
                   onChange={(e) => changeValue(e.target.value)}/>
        )
    }

    function renderUnknown(field) {
        return (
            <p>Some nose picker input</p>
        )
    }

    function renderInput(field) {
        switch (field.type) {
            case "TEXT" :
                return renderText(field);
            case "IMAGE" :
                return renderImage(field);
            case "ATTACHMENT" :
                return renderAttachment(field);
            case "DATE" :
                return renderDate(field);
            default:
                return renderUnknown(field);
        }
    }

    return (
        <>
            <div className="m-5 border border-light-subtle p-3 rounded-2">
                <label htmlFor="inputFieldName" className="col-sm-2 col-form-label">{field.name}</label>
                <div className="col-sm-10">
                    {
                        renderInput(field)
                    }
                </div>
            </div>
        </>
    )
}

export default FieldEntry
