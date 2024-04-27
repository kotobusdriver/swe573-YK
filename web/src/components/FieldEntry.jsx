import {useState} from "react";

function FieldEntry({field, onValueChange}) {
    const [value, setValue] = useState(field.name);

    function changeValue(v) {
        setValue(v)
        onValueChange(v)
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
            <input type="file" className="form-control" id="inputFieldImage" accept="image/png, image/jpeg" onChange={(e) => changeValue(e.target.files[0])}/>
        )
    }

    function renderAttachment(field) {
        return (
        <input type="file" className="form-control" id="inputFieldAttachment"onChange={(e) => changeValue(e.target.files[0])}/>
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
