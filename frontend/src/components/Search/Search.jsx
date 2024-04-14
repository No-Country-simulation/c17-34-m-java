import React from "react";
import "./search.css";
import { SearchIcon } from "./../Icons/Basic/SearchIcon";

const Search = () => {
  return (
    <div className="mobile-search">
      <form className="search-form">
        <input 
          type="text" 
          name="search" 
          placeholder="Buscar por nombre o lugar" 
          className="mobile-search-input"
          />
          <button type="submit">
           <SearchIcon width="20" height="20"/>
          </button>
      </form>
    </div>
  );
};

export default Search;
